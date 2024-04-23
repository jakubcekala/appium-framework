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
        this.setCapability("platformName", System.getProperty("platformName") != null ? System.getProperty("platformName") : platformName);
        this.setCapability("deviceName", System.getProperty("deviceName") != null ? System.getProperty("deviceName") : deviceName);
        this.setCapability("udid", System.getProperty("udid") != null ? System.getProperty("udid") : udid);
        this.setCapability("platformVersion", System.getProperty("platformVersion") != null ? System.getProperty("platformVersion") : platformVersion);
        this.setCapability("automationName", System.getProperty("iOSAutomationName") != null ? System.getProperty("iOSAutomationName") : props.getProperty("iOSAutomationName"));
        this.setCapability("bundleId", System.getProperty("iOSBundleId") != null ? System.getProperty("iOSBundleId") : props.getProperty("iOSBundleId"));
        String iOSAppURL = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
        this.setCapability("app", System.getProperty("iOSAppURL") != null ? System.getProperty("iOSAppURL") : iOSAppURL);
    }
}
