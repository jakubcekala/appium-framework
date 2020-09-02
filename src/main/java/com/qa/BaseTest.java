package com.qa;


import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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
            caps.setCapability("automationName", props.getProperty("androidAutomationName"));
            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
            String appURL = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
            caps.setCapability("app", appURL);

            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new AndroidDriver(url, caps);

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
