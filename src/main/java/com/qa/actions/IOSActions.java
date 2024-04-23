package com.qa.actions;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class IOSActions extends Actions {

    private final AppiumDriver driver;

    public IOSActions(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void scrollToElement(WebElement el, String childLocAttr, String childLocValue) {
        String elementID = el.getAttribute("UID");
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "true");
        driver.executeScript("mobile:scroll", scrollObject);
    }
}
