package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportHelper {

    static ExtentReports extentReports;

    public static ExtentReports getExtentReport() {
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
