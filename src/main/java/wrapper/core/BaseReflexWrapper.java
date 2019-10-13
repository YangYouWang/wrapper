package wrapper.core;

import wrapper.annotion.Wrapper;
import wrapper.consts.ConfigConsts;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射获取属性值
 * @author yangyouwang
 */
public abstract class BaseReflexWrapper {

    Map<String, Object> wrapTheMap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                field.setAccessible(true);
                String fieldValue = field.get(obj).toString();
                if (!ConfigConsts.SERIAL_VERSION_UID.equals(fieldName)
                        && field.isAnnotationPresent(Wrapper.class)) {
                    Wrapper wrapperAnnotation = field.getAnnotation(Wrapper.class);
                    BaseReflexWrapper wrapper = WrapperFactory.createWrapper(wrapperAnnotation.dictType());
                    result.put(fieldName, wrapper.wrapperType(wrapperAnnotation, fieldName ,fieldValue));
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

    /**
     * wrapper 类型
     * @param wrapperAnnotation 注解类
     * @param fieldName 属性
     * @param fieldValue 值
     * @return 包装Map
     */
    public abstract Map<String, Object> wrapperType(Wrapper wrapperAnnotation, String fieldName, String fieldValue);
}
