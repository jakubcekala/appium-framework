package com.qa.actions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class AndroidActions extends Actions {

    private final AppiumDriver driver;

    public AndroidActions(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void scrollToElement(WebElement el, String childLocAttr, String childLocValue) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()."
                        + childLocAttr + "(\"" + childLocValue + "\"));"
        ));
    }
}
