package com.qa.actions;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Actions {

    private final AppiumDriver driver;

    public Actions(AppiumDriver driver) {
        this.driver = driver;
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
        String text = "";
        if (driver instanceof AndroidDriver) {
            text = getAttribute(element, "text");
        } else if (driver instanceof IOSDriver) {
            text = getAttribute(element, "label");
        }
        return text;
    }

    public abstract void scrollToElement(MobileElement el, String childLocAttr, String childLocValue);
}
