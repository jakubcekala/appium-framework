package com.qa.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class ActionsFactory {

    public Actions actions;

    public ActionsFactory(AppiumDriver driver) {
        if (driver instanceof AndroidDriver) {
            actions = new AndroidActions(driver);
        } else if (driver instanceof IOSDriver) {
            actions = new IOSActions(driver);
        }
    }
}
