package com.qa.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AndroidCapability extends DesiredCapabilities {
    Properties props = new Properties();
    String propsFileName = "config.properties";

    public AndroidCapability(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
        props.load(inputStream);
        this.setCapability("platformName", System.getProperty("platformName") != null ? System.getProperty("platformName") : platformName);
        this.setCapability("deviceName", System.getProperty("deviceName") != null ? System.getProperty("deviceName") : deviceName);
        this.setCapability("automationName", System.getProperty("androidAutomationName") != null ? System.getProperty("androidAutomationName") : props.getProperty("androidAutomationName"));
        this.setCapability("appPackage", System.getProperty("androidAppPackage") != null ? System.getProperty("androidAppPackage") : props.getProperty("androidAppPackage"));
        this.setCapability("appActivity", System.getProperty("androidAppActivity") != null ? System.getProperty("androidAppActivity") : props.getProperty("androidAppActivity"));
        String androidAppURL = getClass().getResource(System.getProperty("androidAppLocation") != null ? System.getProperty("androidAppLocation") : props.getProperty("androidAppLocation")).getFile();
        this.setCapability("app", androidAppURL);

        if (emulator.equalsIgnoreCase("true")) {
            this.setCapability("platformVersion", System.getProperty("platformVersion") != null ? System.getProperty("platformVersion") : platformVersion);
            this.setCapability("avd", System.getProperty("deviceName") != null ? System.getProperty("deviceName") : deviceName);
        } else {
            this.setCapability("udid", System.getProperty("udid") != null ? System.getProperty("udid") : udid);
        }
    }
}
