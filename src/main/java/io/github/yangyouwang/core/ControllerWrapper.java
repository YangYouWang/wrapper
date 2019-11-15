package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Wrapper;
import io.github.yangyouwang.consts.ConfigConsts;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper包装类
 *
 * @author yangyouwang
 */
@Component
public class ControllerWrapper {


    private static ControllerWrapper instance;

    private ControllerWrapper(){}

    public static ControllerWrapper getInstance() {
        if (instance == null) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            instance = (ControllerWrapper) applicationContext.getBean("controllerWrapper");
        }
        return instance;
    }

    public List<Map<String, Object>> wrap(List<?> objs) {
        return objs.stream().map(this::wrap).collect(Collectors.toList());
    }
    public Map<String, Object> wrap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                String fieldValue = field.get(obj).toString();
                if (!ConfigConsts.SERIAL_VERSION_UID.equals(fieldName)
                        && field.isAnnotationPresent(Wrapper.class)) {
                    Wrapper wrapperAnnotation = field.getAnnotation(Wrapper.class);
                    BaseReflexWrapper wrapper = WrapperFactory.createWrapper(wrapperAnnotation.dictType());
                    result.putAll(wrapper.wrapTheMap(wrapperAnnotation, fieldName ,fieldValue));
                    continue;
                }
                result.put(fieldName, fieldValue);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有访问权限.");
        } catch (Exception e) {
            throw new RuntimeException("出错了.");
        }
    }
}
