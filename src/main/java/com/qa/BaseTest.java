package com.qa;


import com.qa.capabilities.AndroidCapability;
import com.qa.capabilities.iOSCapability;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

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
                    iOSCapability iOSCaps = new iOSCapability(platformName, deviceName);
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
        closeApp();
        launchApp();
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

    public void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public String getText(MobileElement element) {
        waitForVisibility(element);
        switch (platform) {
            case "Android":
                return getAttribute(element, "text");
            case "iOS":
                return getAttribute(element, "label");
            default:
                return null;
        }
    }

    public void closeApp() {
        driver.closeApp();
    }

    public void launchApp() {
        driver.launchApp();
    }

    public void androidScrollToElement(String childLocAttr, String childLocValue) {
        ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()."
                        + childLocAttr + "(\"" + childLocValue + "\"));"
        );
    }

    public void androidScrollToElementWithVisibleText(String visibleText) {
        if (getPlatform().equalsIgnoreCase("Android")) {
            ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\""
                            + visibleText + "\").instance(0))"
            );
        }
    }

    public void iOSScrollToElement(MobileElement el) {
        String elementID = el.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
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
