package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReportHelper;
import com.qa.reports.ScreenshotHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentReports extentReports = ExtentReportHelper.getExtentReport();
    private static ExtentTest test;
    private AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getTestClass().getName());
        stepLog("=========== STARTING TEST: " + result.getMethod().getMethodName() + "===========");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(
                ScreenshotHelper.getScreenshotPath(result.getTestName(), driver),
                result.getTestName()
        );
        test.fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    public static void stepLog(String message) {
        test.log(Status.INFO, message);
    }
}
