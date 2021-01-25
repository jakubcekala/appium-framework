package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailsPage extends MenuPage {
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

    public String getProductTitle() {
        String title = getText(productTitle);
        System.out.println("Product Title is: " + title);
        return title;
    }

    public String getProductDescription() {
        String description = getText(productDescription);
        System.out.println("Product Description is: " + description);
        return description;
    }

    public String getProductPrice() {
        String price = getText(productPrice);
        System.out.println("Product Price is: " + price);
        return price;
    }

    public ProductDetailsPage scrollToPrice() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            androidScrollToElement("description", "test-Price");
        } else if (getPlatform().equalsIgnoreCase("iOS")) {
            iOSScrollToElement(productPrice);
        }
        return this;
    }

    public ProductDetailsPage pressAddToCartButton() {
        click(addToCartButton);
        return this;
    }

    public ProductDetailsPage scrollToAddToCartButton() {
        if (getPlatform().equalsIgnoreCase("Android")) {
            androidScrollToElement("description", "test-ADD TO CART");
        } else if (getPlatform().equalsIgnoreCase("iOS")) {
            iOSScrollToElement(addToCartButton);
        }
        return this;
    }

    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }

    public ProductsPage pressBackToProductsButton() {
        click(backToProductsButton);
        System.out.println("Pressed \"Back to products\" button");
        return new ProductsPage();
    }
}
