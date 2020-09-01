package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass() {

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = new LoginPage();
        System.out.println("\n********** starting test: " + method.getName() + " **********\n");
    }

    @Test
    public void invalidUsername() {
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void invalidPassword() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("invalidPassword");
        loginPage.pressLoginButton();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error: " + actualErrorText + "\n" + "Expected error: " + expectedErrorText);

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
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
