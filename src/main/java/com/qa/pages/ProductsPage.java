package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage extends BasePage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView\n")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
    private MobileElement productTitleText;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    private MobileElement SLBTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    private MobileElement SLBPrice;

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(productTitleText);
    }

    public String getSLBTitle() {
        String title = getText(SLBTitle);
        System.out.println("Product page title: " + title);
        return title;
    }

    public String getSLBPrice() {
        String price = getText(SLBPrice);
        System.out.println("Product page price: " + price);
        return price;
    }

    public ProductDetailsPage pressSLBTitle() {
        System.out.println("Pressed title - " + getText(SLBTitle));
        click(SLBTitle);
        return new ProductDetailsPage(driver);
    }
}
