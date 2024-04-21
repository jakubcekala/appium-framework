package com.qa.pages;

import com.qa.actions.Actions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Actions {

    AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n" + "")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")

    private MobileElement hamburgerMenuButton;

    public BasePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HamburgerMenuPage pressHamburgerMenuButton() {
        click(hamburgerMenuButton);
        System.out.println("Hamburger menu button pressed");
        return new HamburgerMenuPage(driver);
    }
}
