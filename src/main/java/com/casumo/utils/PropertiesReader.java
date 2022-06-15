package com.casumo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String getBaseUrl() {
        return getProperty("base_url");
    }

    private static String getProperty(String propertyName) {
        if (System.getProperty(propertyName) == null) {
            return getPropertyFromFile(propertyName);
        } else {
            return System.getProperty(propertyName);
        }
    }

    private static String getPropertyFromFile(String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/test/resources/env/qa/env.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot read property value for " + propertyName);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties.getProperty(propertyName);
    }
}
