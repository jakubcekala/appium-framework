package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream dataIS;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFilename = "data/loginUsers.json";
            dataIS = getClass().getClassLoader().getResourceAsStream(dataFilename);
            JSONTokener tokener = new JSONTokener(dataIS);
            loginUsers = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dataIS != null) {
                dataIS.close();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = new LoginPage();
        System.out.println("\n********** starting test: " + method.getName() + " **********\n");
    }

    @Test
    public void invalidUsername() {
        loginPage.enterUsername(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void invalidPassword() {
        loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.pressLoginButton();

        String actualTitle = productsPage.getTitle();
        String expectedTitle = "PRODUCTS";
        System.out.println("Actual title: " + actualTitle + "\n" + "Expected title: " + expectedTitle);

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() {

    }
}
