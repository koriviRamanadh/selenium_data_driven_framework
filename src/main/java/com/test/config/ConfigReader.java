package com.test.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String configPath = "src/main/java/com/test/resources/config.properties";

    static{
        try(FileInputStream fin = new FileInputStream(configPath)){
            properties = new Properties();
            properties.load(fin);
        }catch(Exception e){
            throw new RuntimeException("Failed to load the config.properties "+ e.getMessage());
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if(value == null){
            throw new RuntimeException("Property" + key +"is not found in the config.properties");
        }
        return value.trim();
    }

    public static String getFilePath(){
        return properties.getProperty("filepath");
    }

    public static String getBrowser(){
        return properties.getProperty("browser");
    }

    public static int getImplicitWait(){
        return Integer.parseInt(getProperty("implicitWait"));
    }

    public static int getExplictWait(){
        return Integer.parseInt(getProperty("explicitWait"));
    }

    public static int getPageLoadTimeouts(){
        return Integer.parseInt(getProperty("pageLoadTimeout"));
    }

    public static String getURL(){
        return properties.getProperty("url");
    }
}
