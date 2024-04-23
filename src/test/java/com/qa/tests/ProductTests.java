package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;

public class ProductTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        loginUsers = getTestDataJson("data/loginUsers.json");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validateProductOnProductsPage() {
        JSONObject validUser = loginUsers.getJSONObject("validUser");
        productsPage = loginPage.login(validUser.getString("username"), validUser.getString("password"));
        SoftAssert softAssert = new SoftAssert();

        String SLBTitle = productsPage.getSLBTitle();
        softAssert.assertEquals(SLBTitle, strings.get("products_page_SLB_title"));

        String SLBPrice = productsPage.getSLBPrice();
        softAssert.assertEquals(SLBPrice, strings.get("products_page_SLB_price"));

        softAssert.assertAll();
    }

    @Test
    public void validateProductOnProductsDetailsPage() {
        JSONObject validUser = loginUsers.getJSONObject("validUser");
        productsPage = loginPage.login(validUser.getString("username"), validUser.getString("password"));
        SoftAssert softAssert = new SoftAssert();

        productDetailsPage = productsPage.clickSLBTitle();

        String SLBTitle = productDetailsPage.getProductTitle();
        softAssert.assertEquals(SLBTitle, strings.get("product_details_SLB_title"));

        String SLBDescription = productDetailsPage.getProductDescription();
        softAssert.assertEquals(SLBDescription, strings.get("product_details_SLB_description"));

        productDetailsPage.scrollToPrice();
        String SLBPrice = productDetailsPage.getProductPrice();
        softAssert.assertEquals(SLBPrice, strings.get("product_details_SLB_price"));

        productDetailsPage.scrollToAddToCartButton();
        softAssert.assertEquals(productDetailsPage.isAddToCartButtonDisplayed(), true);

        productsPage = productDetailsPage.clickBackToProductsButton();

        softAssert.assertAll();
    }
}
