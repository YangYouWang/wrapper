package com.yangyouwang.wrapper.core;

import com.yangyouwang.wrapper.consts.ConfigConsts;

/**
 * warpper 工厂类
 * @author yangyouwang
 */
class WrapperFactory {

    static BaseReflexWrapper createWrapper(String dictType) {
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
