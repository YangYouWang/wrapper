package io.github.yangyouwang.core;

import io.github.yangyouwang.consts.ConfigConsts;

/**
 * wrapper 工厂类
 * @author yangyouwang
 */
public class FactoryWrapper {

    protected static BaseReflexWrapper createWrapper(String dictType) {
        switch (dictType) {
            case ConfigConsts.WRAPPER_TYPE_ARRAY:
                return new ArrayWrapper();
            case ConfigConsts.WRAPPER_TYPE_CONFIG:
                return new ConfigWrapper();
            default:
                return null;
        }
    }
}
