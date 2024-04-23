package com.qa.pages;

import com.qa.listeners.TestListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
    private WebElement productTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
    private WebElement productDescription;

    @AndroidFindBy(accessibility = "test-Price")
    @iOSXCUITFindBy(id = "test-Price")
    private WebElement productPrice;

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    @iOSXCUITFindBy(id = "test-ADD TO CART")
    private WebElement addToCartButton;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy(id = "test-BACK TO PRODUCTS")
    private WebElement backToProductsButton;

    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        String title = actions.getText(productTitle);
        TestListener.stepLog("Product Title is: " + title);
        return title;
    }

    public String getProductDescription() {
        String description = actions.getText(productDescription);
        TestListener.stepLog("Product Description is: " + description);
        return description;
    }

    public String getProductPrice() {
        String price = actions.getText(productPrice);
        TestListener.stepLog("Product Price is: " + price);
        return price;
    }

    public ProductDetailsPage scrollToPrice() {
        TestListener.stepLog("Scrolling to price field");
        actions.scrollToElement(productPrice,"description", "test-Price");
        return this;
    }

    public ProductDetailsPage clickAddToCartButton() {
        TestListener.stepLog("Clicking add to cart button");
        actions.click(addToCartButton);
        return this;
    }

    public ProductDetailsPage scrollToAddToCartButton() {
        TestListener.stepLog("Scrolling to add to cart field");
        actions.scrollToElement(productPrice, "description", "test-ADD TO CART");
        return this;
    }

    public boolean isAddToCartButtonDisplayed() {
        TestListener.stepLog("Checking if add to cart button is displayed");
        return addToCartButton.isDisplayed();
    }

    public ProductsPage clickBackToProductsButton() {
        TestListener.stepLog("Clicking \"Back to products\" button");
        actions.click(backToProductsButton);
        return new ProductsPage(driver);
    }
}
