package io.github.yangyouwang.core;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * config wrapper
 * @author yangyouwang
 */
public class ConfigWrapper extends BaseReflexWrapper {

    @Override
    protected Map<String, Object> wrapTheType(String dictName, String dictData, String fieldName, String fieldValue) {
        String dictDataValue = CustomizedPropertyPlaceholderConfigurer.getContextProperty(dictData).toString();
        Map<String, Object> result = new HashMap<>(16);
        if (fieldValue.equals(dictDataValue)) {
            if (StringUtils.isEmpty(dictName)) {
                result.put(fieldName, dictDataValue);
                return result;
            }
            result.put(dictName, dictDataValue);
        }
        return result;
    }
}
