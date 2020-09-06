package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseTest {
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView\n" + "")
    @iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
    private MobileElement hamburgerMenuButton;

    public HamburgerMenuPage pressSettingsBtn() {
        click(hamburgerMenuButton);
        return new HamburgerMenuPage();
    }

}
