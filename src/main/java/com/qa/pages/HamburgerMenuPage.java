package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class HamburgerMenuPage extends BasePage {

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(id = "test-LOGOUT")
    private WebElement logoutButton;

    @AndroidFindBy(accessibility = "test-Close")
    @iOSXCUITFindBy(id = "test-Close")
    private WebElement closeButton;

    public HamburgerMenuPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage clickLogoutButton() {
        actions.waitForVisibility(logoutButton);
        actions.click(logoutButton);
        return new LoginPage(driver);
    }

    public ProductsPage clickCloseButton() {
        actions.waitForVisibility(closeButton);
        actions.click(closeButton);
        return new ProductsPage(driver);
    }
}
