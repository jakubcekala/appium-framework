package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private MobileElement productTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private MobileElement productDescription;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private MobileElement backToProductsButton;

    public String getProductTitle() {
        String title = getText(productTitle);
        System.out.println("Product Title is: " + title);
        return title;
    }

    public String getProductDescription() {
        String description = getText(productDescription);
        System.out.println("Product Title is: " + productDescription);
        return description;
    }

    public ProductsPage pressBackToProductsButton() {
        click(backToProductsButton);
        System.out.println("Pressed \"Back to products\" button");
        return new ProductsPage();
    }
}
