package com.qa.pages;

import com.qa.listeners.TestListener;
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
        TestListener.stepLog("Username entered: " + username);
        actions.sendKeys(usernameTextField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        TestListener.stepLog("Password entered: " + password);
        actions.sendKeys(passwordTextField, password);
        return this;
    }

    public ProductsPage pressLoginButton() {
        TestListener.stepLog("Login button pressed");
        actions.click(loginButton);
        return new ProductsPage(driver);
    }

    public ProductsPage login(String username, String password) {
        TestListener.stepLog("Login with:\nUsername: " + username + "\nPassword: " + password);
        enterUsername(username);
        enterPassword(password);
        return pressLoginButton();
    }

    public String getErrorText() {
        String error = actions.getText(errorText);
        TestListener.stepLog("The error is: " + error);
        System.out.println("The error is: " + error);
        return error;
    }
}
