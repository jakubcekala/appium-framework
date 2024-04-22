package com.qa.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(id = "test-Username")
    private WebElement usernameTextField;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(id = "test-Password")
    private WebElement passwordTextField;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(id = "test-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
    private WebElement errorText;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        actions.clear(usernameTextField);
        actions.sendKeys(usernameTextField, username);
        System.out.println("Username entered: " + username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actions.clear(passwordTextField);
        actions.sendKeys(passwordTextField, password);
        System.out.println("Password entered: " + password);
        return this;
    }

    public ProductsPage pressLoginButton() {
        actions.click(loginButton);
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
        String error = actions.getText(errorText);
        System.out.println("The error is: " + error);
        return error;
    }
}
