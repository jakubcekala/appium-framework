package com.qa;


import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    InputStream inputStream;
    InputStream stringsis;

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {

        URL url;
        try {

            props = new Properties();
            String propsFileName = "config.properties";
            String stringsXMLFileName = "strings/strings.xml";

            inputStream = getClass().getClassLoader().getResourceAsStream(propsFileName);
            props.load(inputStream);

            stringsis = getClass().getClassLoader().getResourceAsStream(stringsXMLFileName);
            strings = TestUtils.parseStringXML(stringsis);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("deviceName", deviceName);
            switch (platformName) {
                case "Android":
                    caps.setCapability("automationName", props.getProperty("androidAutomationName"));
                    caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
                    String androidAppURL = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
                    caps.setCapability("app", androidAppURL);

                    url = new URL(props.getProperty("appiumURL"));
                    driver = new AndroidDriver(url, caps);
                    break;
                case "iOS":
                    caps.setCapability("automationName", props.getProperty("iOSAutomationName"));
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
                    String iOSAppURL = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
                    caps.setCapability("app", iOSAppURL);

                    url = new URL(props.getProperty("appiumURL"));
                    driver = new IOSDriver(url, caps);
                    break;
                default:
                    throw new Exception("Invalid platform name! - " + platformName);
            }

            String sessionId = driver.getSessionId().toString();
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

    public void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
