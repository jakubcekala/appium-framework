package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BaseTest {
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

    public LoginPage() {
    }

    public LoginPage enterUsername(String username) {
        clear(usernameTextField);
        sendKeys(usernameTextField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        clear(passwordTextField);
        sendKeys(passwordTextField, password);
        return this;
    }

    public ProductsPage pressLoginButton() {
        click(loginButton);
        return new ProductsPage();
    }

    public String getErrorText() {
        return getText(errorText);
    }
}
