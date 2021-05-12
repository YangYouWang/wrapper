package io.github.yangyouwang.factory;

import io.github.yangyouwang.consts.ConfigConsts;
import io.github.yangyouwang.core.ArrayWrapper;
import io.github.yangyouwang.core.CachePool;
import io.github.yangyouwang.core.ConfigWrapper;
import io.github.yangyouwang.core.MasterWrapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * @author yangyouwang
 * @title: FactoryWrapper
 * @projectName wrapper
 * @description: wrapper 工厂类
 * @date 2021/3/199:12 PM
 */
@Component
public class FactoryWrapper implements FactoryBean {

    @Value("${wrapper.type}")
    private String type;

    @Override
    public MasterWrapper getObject() {
        Assert.notNull(type,"wrapper类型未配置");
        if (ConfigConsts.WRAPPER_TYPE_ARRAY.equals(type)) {
            return new MasterWrapper(new ArrayWrapper(), CachePool.PROCESSORS);
        }
        if (ConfigConsts.WRAPPER_TYPE_CONFIG.equals(type)) {
            return new MasterWrapper(new ConfigWrapper(), CachePool.PROCESSORS);
        }
        throw new RuntimeException("参数错误");
    }

    @Override
    public Class<MasterWrapper> getObjectType() {
        return MasterWrapper.class;
    }
}
