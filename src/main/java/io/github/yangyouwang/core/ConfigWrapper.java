package io.github.yangyouwang.core;

import io.github.yangyouwang.util.PropertiesUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * config wrapper
 * @author yangyouwang
 */
public class ConfigWrapper extends BaseReflexWrapper {

    private PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();

    @Override
    protected Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue) {
        String dictDataValue = propertiesUtil.read(dictData);
        String dictKey = dictData.split("\\.")[1];
        Map<String, Object> result = new HashMap<>(16);
        if (fieldValue.equals(dictKey)) {
            if (dictName.isEmpty()) {
                result.put(fieldName, dictDataValue);
                return result;
            }
            result.put(dictName, dictDataValue);
        }
        return result;
    }
}
