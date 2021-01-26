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
        this.setCapability("platformName", platformName);
        this.setCapability("deviceName", deviceName);
        this.setCapability("automationName", props.getProperty("androidAutomationName"));
        this.setCapability("appPackage", props.getProperty("androidAppPackage"));
        this.setCapability("appActivity", props.getProperty("androidAppActivity"));
        String androidAppURL = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
        this.setCapability("app", androidAppURL);

        if (emulator.equalsIgnoreCase("true")) {
            this.setCapability("platformVersion", platformVersion);
            this.setCapability("avd", deviceName);
        } else {
            this.setCapability("udid", udid);
        }
    }
}
