package com.qa.reports;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    public static String getScreenshotPath(String testName, AppiumDriver driver) {
        File source = driver.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(System.getProperty("user.dir") + "/reports/" + testName + ".png");
        try {
            FileHandler.copy(source, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationFile.getPath();
    }
}
