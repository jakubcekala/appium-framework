package com.qa;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected AppiumDriver driver;

    @BeforeTest
    public void beforeTest() {

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformName", "9.0");
            caps.setCapability("platformName", "device name");
            caps.setCapability("platformName", "UiAutomator2");
            caps.setCapability("platformName", "com.swaglabsmobileapp");
            caps.setCapability("platformName", "com.swaglabmobileapp.SplashActivity");

            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new AndroidDriver(url, caps);

            String sessionId = driver.getSessionId().toString();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void afterTest() {

    }

}
