package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductTests extends BaseTest {

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
            throw e;
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
        productsPage = loginPage.login(
                loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password")
        );
    }

    @Test

    @AfterMethod
    public void afterMethod() {
        SoftAssert softAssert = new SoftAssert();

        String SLBTitle = productsPage.getSLBTitle();
        softAssert.assertEquals(SLBTitle, strings.get("products_page_SLB_title"));

        String SLBPrice = productsPage.getSLBPrice();
        softAssert.assertEquals(SLBPrice, strings.get("products_page_SLB_price"));

        softAssert.assertAll();
    }

    @AfterClass
    public void afterClass() {

    }
}
