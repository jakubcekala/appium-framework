package com.qa.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.HashMap;

public class IOSActions extends Actions {

    private final AppiumDriver driver;

    public IOSActions(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void scrollToElement(MobileElement el, String childLocAttr, String childLocValue) {
        String elementID = el.getId();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
    }
}
