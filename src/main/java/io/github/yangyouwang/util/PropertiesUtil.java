package io.github.yangyouwang.util;

import io.github.yangyouwang.consts.ConfigConsts;

import java.io.*;
import java.util.Properties;

/**
 * 工具类
 * @author yangyouwang
 */
public class PropertiesUtil {

    private PropertiesUtil() {
    }

    private static class SingletonHolder {
        private final static PropertiesUtil instance = new PropertiesUtil();
    }

    public static PropertiesUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 读取key字段，配置文件在classes根路径下xx.properties，在子路径下xx/xx.properties
     */
    public String read(String key) {
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            // 获取外部项目配置文件
            inputStream = this.getClass().getResourceAsStream(ConfigConsts.CONFIG_PATH);
            if (null == inputStream) {
                // 读取本项目下
                inputStream = getClass().getClassLoader().getResourceAsStream(ConfigConsts.CONFIG_NAME);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(key);
    }
}
