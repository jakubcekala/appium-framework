package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.HamburgerMenuPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
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
    HamburgerMenuPage hamburgerMenuPage;
    ProductDetailsPage productDetailsPage;
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
        loginPage = new LoginPage(driver);
        System.out.println("\n********** starting test: " + method.getName() + " **********\n");
        productsPage = loginPage.login(
                loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password")
        );
    }

    @Test
    public void validateProductOnProductsPage() {
        SoftAssert softAssert = new SoftAssert();

        String SLBTitle = productsPage.getSLBTitle();
        softAssert.assertEquals(SLBTitle, strings.get("products_page_SLB_title"));

        String SLBPrice = productsPage.getSLBPrice();
        softAssert.assertEquals(SLBPrice, strings.get("products_page_SLB_price"));

        softAssert.assertAll();
    }

    @Test
    public void validateProductOnProductsDetailsPage() {
        SoftAssert softAssert = new SoftAssert();

        productDetailsPage = productsPage.pressSLBTitle();

        String SLBTitle = productDetailsPage.getProductTitle();
        softAssert.assertEquals(SLBTitle, strings.get("product_details_SLB_title"));

        String SLBDescription = productDetailsPage.getProductDescription();
        softAssert.assertEquals(SLBDescription, strings.get("product_details_SLB_description"));

        productDetailsPage.scrollToPrice();
        String SLBPrice = productDetailsPage.getProductPrice();
        softAssert.assertEquals(SLBPrice, strings.get("product_details_SLB_price"));

        productDetailsPage.scrollToAddToCartButton();
        softAssert.assertEquals(productDetailsPage.isAddToCartButtonDisplayed(), true);

        productsPage = productDetailsPage.pressBackToProductsButton();

        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        hamburgerMenuPage = productsPage.pressHamburgerMenuButton();
        loginPage = hamburgerMenuPage.clickLogoutButton();
    }
}
