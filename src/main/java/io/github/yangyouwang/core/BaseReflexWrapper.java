package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Sort;
import io.github.yangyouwang.annotion.Wrapper;
import io.github.yangyouwang.consts.ConfigConsts;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射获取属性值
 * @author yangyouwang
 */
public abstract class BaseReflexWrapper extends BaseWorkerWrapper {

    /**
     * 反射获取注解属性
     * @param obj 对象
     * @return Map
     */
    @Override
    public final Map<String, Object> wrapTheMap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        // 获取Field
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                // 获取字段相关属性
                String fieldName = field.getName();
                field.setAccessible(true);
                String fieldValue = field.get(obj).toString();
                // 非字段
                if (!ConfigConsts.SERIAL_VERSION_UID.equals(fieldName)) {
                    if(field.isAnnotationPresent(Wrapper.class)) {
                        // 获取注解相关属性
                        Wrapper wrapperAnnotation = field.getAnnotation(Wrapper.class);
                        String[] dictData = wrapperAnnotation.dictData();
                        String dictName = wrapperAnnotation.name();
                        // 选择类型
                        BaseReflexWrapper wrapper = FactoryWrapper.createWrapper(wrapperAnnotation.dictType());
                        for (String dict : dictData) {
                            result.putAll(wrapper.wrapTheType(dictName, dict, fieldName, fieldValue));
                        }
                        continue;
                    }
                    if(field.isAnnotationPresent(Sort.class)) {
                        CachePool.FIELD_NAME_SET.add(fieldName);
                    }
                    result.put(fieldName, fieldValue);
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有访问权限.");
        } catch (NullPointerException e) {
            throw new RuntimeException("空指针异常了.");
        }
    }

    /**
     * wrapper类型
     * @param dictName 字典名称
     * @param dictData 字典数据
     * @param fieldName 属性
     * @param fieldValue 值
     * @return 包装Map
     */
    protected abstract Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue);
}
