package com.yangyouwang.wrapper.core;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * config wrapper
 * @author yangyouwang
 */
public class ConfigWrapper extends BaseReflexWrapper {

    @Override
    public Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue) {
        Map<String, Object> result = new HashMap<>(16);
        String dictDataValue = CustomizedPropertyPlaceholderConfigurer.getContextProperty(dictData).toString();
        if (fieldValue.equals(dictDataValue)) {
            if (StringUtils.isEmpty(dictName)) {
                result.put(fieldName, dictDataValue);
            } else {
                result.put(dictName, dictDataValue);
            }
        }
        return result;
    }
}
