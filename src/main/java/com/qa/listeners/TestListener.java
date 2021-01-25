package com.qa.listeners;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }

        BaseTest baseTest = new BaseTest();
        File screenshotFile = baseTest.getDriver().getScreenshotAs(OutputType.FILE);

        Map<String, String> parameters = result.getTestContext().getCurrentXmlTest().getAllParameters();
        String screenshotPath = "Screenshots" + File.separator + parameters.get("platformName") + "_"
                + parameters.get("platformVersion") + "_" + parameters.get("deviceName") + File.separator
                + TestUtils.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName()
                + File.separator + result.getName() + ".png";
        try {
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(screenshotFile, destFile);
            Reporter.log("Screenshot:");
            Reporter.log("<a href='" + destFile.getAbsolutePath()
                    + "'> <img src='" + destFile.getAbsolutePath()
                    + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
