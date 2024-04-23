package com.qa.pages;

import com.qa.actions.ActionsFactory;
import com.qa.listeners.TestListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends ActionsFactory {

    AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n" + "")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")

    private WebElement hamburgerMenuButton;

    public BasePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HamburgerMenuPage clickHamburgerMenuButton() {
        TestListener.stepLog("Clicking on hamburger menu button");
        actions.click(hamburgerMenuButton);
        return new HamburgerMenuPage(driver);
    }
}
