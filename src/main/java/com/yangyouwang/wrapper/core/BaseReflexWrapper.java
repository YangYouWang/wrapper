package com.yangyouwang.wrapper.core;

import com.yangyouwang.wrapper.annotion.Wrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 反射获取属性值
 * @author yangyouwang
 */
public abstract class BaseReflexWrapper {

    Map<String, Object> wrapTheMap(Wrapper wrapperAnnotation, String fieldName, String fieldValue) {
        String[] dictData = wrapperAnnotation.dictData();
        String dictName = wrapperAnnotation.name();
        Map<String, Object> result = new HashMap<>(16);
        for (String dict : dictData) {
             result.putAll(wrapTheType(dictName, dict, fieldName, fieldValue));
        }
        return result;
    }

    /**
     * wrapper 类型
     * @param dictName 字典名称
     * @param dictData 字典数据
     * @param fieldName 属性
     * @param fieldValue 值
     * @return 包装Map
     */
    abstract Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue);
}
