package io.github.yangyouwang.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author yangyouwang
 * @title: WrapperProperties
 * @projectName wrapper
 * @description: 包装配置类
 * @date 2021/3/193:16 PM
 */
@ConfigurationProperties(prefix = "wrapper")
public class WrapperProperties {

    /**
     * 类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
