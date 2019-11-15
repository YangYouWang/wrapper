package io.github.yangyouwang.core;

import io.github.yangyouwang.core.ArrayWrapper;
import io.github.yangyouwang.core.BaseReflexWrapper;
import io.github.yangyouwang.core.ConfigWrapper;
import io.github.yangyouwang.consts.ConfigConsts;

/**
 * warpper 工厂类
 * @author yangyouwang
 */
public class WrapperFactory {

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
