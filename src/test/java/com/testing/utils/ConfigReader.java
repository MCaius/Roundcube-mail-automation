package com.testing.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    public static void loadConfig(String env) {
        try {
            String filePath = "src/test/resources/config." + env + ".properties";
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config for env: " + env + " - " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

