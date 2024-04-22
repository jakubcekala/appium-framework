package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        loginUsers = getTestDataJson("data/loginUsers.json");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = new LoginPage(driver);
        System.out.println("\n********** starting test: " + method.getName() + " **********\n");
    }

    @Test
    public void invalidUsername() {
        JSONObject invalidUsername = loginUsers.getJSONObject("invalidUsername");
        loginPage.enterUsername(invalidUsername.getString("username"));
        loginPage.enterPassword(invalidUsername.getString("password"));
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = strings.get("error_invalid_username_or_password");
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void invalidPassword() {
        JSONObject invalidPassword = loginUsers.getJSONObject("invalidPassword");
        loginPage.enterUsername(invalidPassword.getString("username"));
        loginPage.enterPassword(invalidPassword.getString("password"));
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = strings.get("error_invalid_username_or_password");
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void userLockedOut() {
        JSONObject invalidPassword = loginUsers.getJSONObject("lockedUser");
        loginPage.enterUsername(invalidPassword.getString("username"));
        loginPage.enterPassword(invalidPassword.getString("password"));
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = strings.get("error_locked_user");
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }



    @Test
    public void successfulLogin() {
        loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        ProductsPage productsPage = loginPage.pressLoginButton();

        String actualTitle = productsPage.getTitle();
        String expectedTitle = strings.get("product_title");
        System.out.println("Actual title: " + actualTitle + "\n" + "Expected title: " + expectedTitle);

        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
