package com.qa.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends Actions {

    private final AppiumDriver driver;

    public AndroidActions(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void scrollToElement(MobileElement el, String childLocAttr, String childLocValue) {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector()."
                        + childLocAttr + "(\"" + childLocValue + "\"));"
        );
    }
}
