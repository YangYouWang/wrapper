package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Wrapper;
import io.github.yangyouwang.util.PropertiesUtil;

/**
 * config wrapper
 * @author yangyouwang
 */
public class ConfigWrapper extends BaseWorkerWrapper {

    PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();

    @Override
    protected String wrapTheType(Wrapper annotation, String fieldName, String fieldValue) {
        String separator = annotation.separator();
        String def = annotation.def();
        String dictDataValue = propertiesUtil.read(fieldName + separator + fieldValue);
        return null == dictDataValue ? def : dictDataValue;
    }
}
