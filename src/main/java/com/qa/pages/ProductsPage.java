package com.qa.pages;

import com.qa.listeners.TestListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView\n")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
    private WebElement productTitleText;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    private WebElement SLBTitle;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    private WebElement SLBPrice;

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getTitle() {
        String title = actions.getText(productTitleText);
        TestListener.stepLog("Product page title: " + title);
        return title;
    }

    public String getSLBTitle() {
        String title = actions.getText(SLBTitle);
        TestListener.stepLog("Product item title: " + title);
        return title;
    }

    public String getSLBPrice() {
        String price = actions.getText(SLBPrice);
        TestListener.stepLog("Product item price: " + price);
        return price;
    }

    public ProductDetailsPage clickSLBTitle() {
        TestListener.stepLog("Clicking item title - " + actions.getText(SLBTitle));
        actions.click(SLBTitle);
        return new ProductDetailsPage(driver);
    }
}
