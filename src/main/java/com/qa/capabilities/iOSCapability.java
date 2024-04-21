package com.qa.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class iOSCapability extends DesiredCapabilities {
    Properties props = new Properties();
    String propsFileName = "config.properties";

    public iOSCapability(String platformName, String deviceName, String udid, String platformVersion) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
        props.load(inputStream);
        this.setCapability("platformName", platformName);
        this.setCapability("deviceName", deviceName);
        this.setCapability("udid", udid);
        this.setCapability("platformVersion", platformVersion);
        this.setCapability("automationName", props.getProperty("iOSAutomationName"));
        this.setCapability("bundleId", props.getProperty("iOSBundleId"));
        String iOSAppURL = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
        this.setCapability("app", iOSAppURL);
    }
}
