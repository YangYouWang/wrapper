package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Wrapper;


/**
 * Array Wrap
 * @author yangyouwang
 */
public class ArrayWrapper extends BaseWorkerWrapper {

    @Override
    protected String wrapTheType(Wrapper annotation, String fieldName, String fieldValue) {
        String[] dictData = annotation.dictData();
        String separator = annotation.separator();
        String def = annotation.def();
        // 选择类型
        for (String dict : dictData) {
            String[] data = dict.split(separator);
            if (fieldValue.equals(data[0])) {
                return data[1];
            }
        }
        return def;
    }
}
