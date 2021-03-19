package io.github.yangyouwang.factory;

import io.github.yangyouwang.config.WrapperProperties;
import io.github.yangyouwang.consts.ConfigConsts;
import io.github.yangyouwang.core.ArrayWrapper;
import io.github.yangyouwang.core.CachePool;
import io.github.yangyouwang.core.ConfigWrapper;
import io.github.yangyouwang.core.MasterWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author yangyouwang
 * @title: FactoryWrapper
 * @projectName wrapper
 * @description: wrapper 工厂类
 * @date 2021/3/199:12 PM
 */
@Component
public class FactoryWrapper {

    @Resource
    private ArrayWrapper array;

    @Resource
    private ConfigWrapper config;

    @Resource
    private WrapperProperties wrapperProperties;

    /**
     * 创建包装返回结果
     * @return 包装返回结果
     */
    public MasterWrapper createResultWrapper() {
        String type = wrapperProperties.getType();
        Assert.notNull(type,"wrapper类型未配置");
        if (ConfigConsts.WRAPPER_TYPE_ARRAY.equals(type)) {
            return new MasterWrapper(array, CachePool.PROCESSORS);
        }
        if (ConfigConsts.WRAPPER_TYPE_CONFIG.equals(type)) {
            return new MasterWrapper(config, CachePool.PROCESSORS);
        }
        throw new RuntimeException("参数错误");
    }
}
