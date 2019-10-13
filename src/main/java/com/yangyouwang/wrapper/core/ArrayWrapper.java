package com.yangyouwang.wrapper.core;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Array Wrap
 * @author yangyouwang
 */
public class ArrayWrapper extends BaseReflexWrapper {


    @Override
    protected Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue) {
        Map<String, Object> result = new HashMap<>(16);
        String[] dict = dictData.split(":");
        if (fieldValue.equals(dict[0])) {
            if (StringUtils.isEmpty(dictName)) {
                result.put(fieldName, dict[1]);
            } else {
                result.put(dictName, dict[1]);
            }
        }
        return result;
    }
}
