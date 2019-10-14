package com.yangyouwang.wrapper.core.factory;

import com.yangyouwang.wrapper.consts.ConfigConsts;
import com.yangyouwang.wrapper.core.ArrayWrapper;
import com.yangyouwang.wrapper.core.BaseReflexWrapper;
import com.yangyouwang.wrapper.core.ConfigWrapper;

/**
 * warpper 工厂类
 * @author yangyouwang
 */
public class WrapperFactory {

    public static BaseReflexWrapper createWrapper(String dictType) {
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
