package com.test;

import com.test.config.ConfigReader;
import com.test.utils.DriverManager;

import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getURL());
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
    }
    
}
