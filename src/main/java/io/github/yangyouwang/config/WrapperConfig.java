package io.github.yangyouwang.config;

import io.github.yangyouwang.core.ArrayWrapper;
import io.github.yangyouwang.core.ConfigWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author yangyouwang
 * @title: WrapperConfig
 * @projectName wrapper
 * @description: 配置类
 * @date 2021/3/193:01 PM
 */
@Configuration
@EnableConfigurationProperties(WrapperProperties.class)
@ComponentScan(basePackages = "io.github.yangyouwang")
@ConditionalOnClass({ConfigWrapper.class,ArrayWrapper.class})
public class WrapperConfig {

    @Bean("config")
    @ConditionalOnMissingBean
    public ConfigWrapper config() {
        return new ConfigWrapper();
    }

    @Bean("array")
    @ConditionalOnMissingBean
    public ArrayWrapper array() {
        return new ArrayWrapper();
    }
}
