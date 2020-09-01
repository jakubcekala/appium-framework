package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "test-Username")
    private MobileElement usernameTextField;
    @AndroidFindBy(accessibility = "test-Password")
    private MobileElement passwordTextField;
    @AndroidFindBy(accessibility = "test-LOGIN")
    private MobileElement loginButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private MobileElement errorText;

    public LoginPage enterUsername(String username) {
        sendKeys(usernameTextField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordTextField, password);
        return this;
    }

    public ProductsPage clickLoginButton() {
        click(loginButton);
        return new ProductsPage();
    }

    public String getErrorText() {
        return getAttribute(errorText, "text");
    }
}
