package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HamburgerMenuPage extends BaseTest {
    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(id = "test-LOGOUT")
    private MobileElement logoutButton;

    @AndroidFindBy(accessibility = "test-Close")
    @iOSXCUITFindBy(id = "test-Close")
    private MobileElement closeButton;

    public LoginPage clickLogoutButton() {
        waitForVisibility(logoutButton);
        click(logoutButton);
        return new LoginPage();
    }

    public ProductsPage clickCloseButton() {
        waitForVisibility(closeButton);
        click(closeButton);
        return new ProductsPage();
    }
}
