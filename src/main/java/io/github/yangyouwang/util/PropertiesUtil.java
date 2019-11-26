package io.github.yangyouwang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

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
     *
     * @param file
     * @param key
     * @return
     */
    public String read(String file, String key) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(file);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        Properties prop = new Properties();
        String value = "";
        try {
            prop.load(bf);
            value = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
