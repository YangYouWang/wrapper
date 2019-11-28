package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Wrapper;
import io.github.yangyouwang.consts.ConfigConsts;

import java.lang.reflect.Field;
import java.util.*;
/**
 * Wrapper包装类
 *
 * @author yangyouwang
 */
public class ControllerWrapper extends WorkerWrapper {

    /**
     * 最大可用的CPU核数
     */
    protected static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static ControllerWrapper instance;

    private ControllerWrapper(){}

    public static ControllerWrapper getInstance() {
        if (instance == null) {
            instance = new ControllerWrapper();
        }
        return instance;
    }

    public List<Map<String, Object>> wrap(List<?> objs) {
        MasterWrapper masterWrapper = new MasterWrapper(this, PROCESSORS * 2);
        for (Object obj : objs) {
            masterWrapper.submit(obj);
        }
        masterWrapper.execute();
        while (true) {
            //判断当所有线程都结束后打印结果
            if (masterWrapper.isComplete()) {
                return masterWrapper.getResult();
            }
        }
    }

    @Override
    public Map<String, Object> wrap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                String fieldValue = field.get(obj).toString();
                if (!ConfigConsts.SERIAL_VERSION_UID.equals(fieldName) && field.isAnnotationPresent(Wrapper.class)) {
                    Wrapper wrapperAnnotation = field.getAnnotation(Wrapper.class);
                    BaseReflexWrapper wrapper = FactoryWrapper.createWrapper(wrapperAnnotation.dictType());
                    result.putAll(wrapper.wrapTheMap(wrapperAnnotation, fieldName ,fieldValue));
                   continue;
                }
                result.put(fieldName, fieldValue);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有访问权限.");
        } catch (NullPointerException e) {
            throw new RuntimeException("空指针异常了.");
        }
    }
}
