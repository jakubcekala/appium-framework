package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(id = "test-Username")
    private MobileElement usernameTextField;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(id = "test-Password")
    private MobileElement passwordTextField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(id = "test-LOGIN")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
    private MobileElement errorText;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        clear(usernameTextField);
        sendKeys(usernameTextField, username);
        System.out.println("Username entered: " + username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        clear(passwordTextField);
        sendKeys(passwordTextField, password);
        System.out.println("Password entered: " + password);
        return this;
    }

    public ProductsPage pressLoginButton() {
        click(loginButton);
        System.out.println("Login button pressed");
        return new ProductsPage(driver);
    }

    public ProductsPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        System.out.println("Login with:\nUsername: " + username + "\nPassword: " + password);
        return pressLoginButton();
    }

    public String getErrorText() {
        String error = getText(errorText);
        System.out.println("The error is: " + error);
        return error;
    }
}
