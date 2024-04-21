package com.qa;


import com.qa.capabilities.AndroidCapability;
import com.qa.capabilities.iOSCapability;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.*;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

@SuppressWarnings("rawtypes")
public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    protected static String platform;
    InputStream inputStream;
    InputStream stringsis;

    @SuppressWarnings("rawtypes")
    @Parameters({"emulator", "platformName", "platformVersion", "udid", "deviceName"})
    @BeforeTest
    public void beforeTest(String emulator, String platformName, String platformVersion, String udid, String deviceName) throws Exception {
        platform = platformName;
        URL url;
        try {
            props = new Properties();
            String propsFileName = "config.properties";
            String stringsXMLFileName = "strings/strings.xml";

            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);

            stringsis = getClass().getClassLoader().getResourceAsStream(stringsXMLFileName);
            strings = TestUtils.parseStringXML(stringsis);

            switch (platformName) {
                case "Android":
                    AndroidCapability androidCaps = new AndroidCapability(emulator, platformName, platformVersion, udid, deviceName);
                    url = new URL(props.getProperty("appiumURL"));
                    driver = new AndroidDriver(url, androidCaps);
                    break;
                case "iOS":
                    iOSCapability iOSCaps = new iOSCapability(platformName, deviceName, udid, platformVersion);
                    url = new URL(props.getProperty("appiumURL"));
                    driver = new IOSDriver(url, iOSCaps);
                    break;
                default:
                    throw new Exception("Invalid platform name! - " + platformName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (stringsis != null) {
                stringsis.close();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        //closeApp();
        launchApp();
    }

    public void closeApp() {
        driver.closeApp();
    }

    public void launchApp() {
        driver.launchApp();
    }

    public String getPlatform() {
        return platform;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
