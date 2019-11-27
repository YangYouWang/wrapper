package io.github.yangyouwang.consts;


/**
 *
 * 系统常量
 * @author yangyouwang
 */
public interface ConfigConsts {

    /**
     * wrapper array类型
     */
    String WRAPPER_TYPE_ARRAY = "array";
    /**
     * wrapper config类型
     */
    String WRAPPER_TYPE_CONFIG = "config";
    /**
     * 配置文件名称
     */
    String CONFIG_NAME = "wrapper.properties";
    /**
     * 配置文件路径
     */
    String CONFIG_PATH = "/resources/" + CONFIG_NAME;
    /**
     * 非正常字段
     */
    String SERIAL_VERSION_UID = "serialVersionUID";
}
