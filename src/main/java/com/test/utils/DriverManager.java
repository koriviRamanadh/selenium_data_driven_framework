package com.test.utils;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.test.config.ConfigReader;

public class DriverManager {

    private static WebDriver driver;

    public static void initDriver(){
        String browser = ConfigReader.getBrowser();

        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeouts()));
    }
    
    public static WebDriver getDriver(){
        if(driver == null){
            throw new RuntimeException("WebDriver not initialized call initDriver first");
        }
        return driver;
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
