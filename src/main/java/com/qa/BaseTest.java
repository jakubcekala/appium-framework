package com.qa;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties props;
    InputStream inputStream;

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) {

        try {

            props = new Properties();
            String propsFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("automationName", props.getProperty("androidAutomationName"));
            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
            URL appURL = getClass().getClassLoader().getResource("androidAppLocation");
            caps.setCapability("app", appURL);

            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new AndroidDriver(url, caps);

            String sessionId = driver.getSessionId().toString();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void afterTest() {

    }

}
