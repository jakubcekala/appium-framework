package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HamburgerMenuPage extends BasePage {

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(id = "test-LOGOUT")
    private MobileElement logoutButton;

    @AndroidFindBy(accessibility = "test-Close")
    @iOSXCUITFindBy(id = "test-Close")
    private MobileElement closeButton;

    public HamburgerMenuPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage clickLogoutButton() {
        waitForVisibility(logoutButton);
        click(logoutButton);
        return new LoginPage(driver);
    }

    public ProductsPage clickCloseButton() {
        waitForVisibility(closeButton);
        click(closeButton);
        return new ProductsPage(driver);
    }
}
