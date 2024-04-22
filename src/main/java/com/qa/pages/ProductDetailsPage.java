package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
    private MobileElement productTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
    private MobileElement productDescription;

    @AndroidFindBy(accessibility = "test-Price")
    @iOSXCUITFindBy(id = "test-Price")
    private MobileElement productPrice;

    @AndroidFindBy(accessibility = "test-ADD TO CART")
    @iOSXCUITFindBy(id = "test-ADD TO CART")
    private MobileElement addToCartButton;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy(id = "test-BACK TO PRODUCTS")
    private MobileElement backToProductsButton;

    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        String title = actions.getText(productTitle);
        System.out.println("Product Title is: " + title);
        return title;
    }

    public String getProductDescription() {
        String description = actions.getText(productDescription);
        System.out.println("Product Description is: " + description);
        return description;
    }

    public String getProductPrice() {
        String price = actions.getText(productPrice);
        System.out.println("Product Price is: " + price);
        return price;
    }

    public ProductDetailsPage scrollToPrice() {
        actions.scrollToElement(productPrice,"description", "test-Price");
        return this;
    }

    public ProductDetailsPage pressAddToCartButton() {
        actions.click(addToCartButton);
        return this;
    }

    public ProductDetailsPage scrollToAddToCartButton() {
        actions.scrollToElement(productPrice, "description", "test-ADD TO CART");
        return this;
    }

    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public ProductsPage pressBackToProductsButton() {
        actions.click(backToProductsButton);
        System.out.println("Pressed \"Back to products\" button");
        return new ProductsPage(driver);
    }
}
