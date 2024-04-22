package com.qa;


import com.qa.capabilities.AndroidCapability;
import com.qa.capabilities.iOSCapability;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

@SuppressWarnings("rawtypes")
public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<>();
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
        launchApp();
    }

    public void closeApp() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).terminateApp(props.getProperty("androidAppPackage"));
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).terminateApp(props.getProperty("iOSBundleId"));
        }
    }

    public void launchApp() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).activateApp(props.getProperty("androidAppPackage"));
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).activateApp(props.getProperty("iOSBundleId"));
        }
    }

    public String getPlatform() {
        return platform;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public JSONObject getTestDataJson(String path) throws IOException {
        InputStream dataIS = getClass().getClassLoader().getResourceAsStream(path);
        JSONTokener tokener = new JSONTokener(dataIS);
        JSONObject JSONObject = new JSONObject(tokener);
        dataIS.close();
        return JSONObject;
    }

    @AfterMethod
    public void afterMethod() {
        closeApp();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
