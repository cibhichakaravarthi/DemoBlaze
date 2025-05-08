package Listerners;

import demoblaze.utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener extends JavaUtility implements ITestListener {

    ExtentTest Test;
    
    public static ExtentReports getExtentReport() {
        String reportPath = "C:\\Users\\cibhi\\eclipse-workspace\\DemoBlaze1\\report\\TestReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("BestBuy Test Report");
        reporter.config().setDocumentTitle("Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Cibhi");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");

        return extent;
    }
    ExtentReports report=getExtentReport();


    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        Test = report.createTest(testName).assignAuthor("Cibhi").assignCategory("AutomationTesting").assignDevice("Windows");
        Test.log(Status.INFO, testName + " execution started");
    }



	@Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        Test.log(Status.PASS, testName + " got successfully executed");
        String screenshotPath = takeScreenShot(testName);
        try {
            Test.addScreenCaptureFromPath(screenshotPath); // Add screenshot to Extent Report
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach screenshot to report", e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        Test.log(Status.FAIL, testName + " got failed");
        Test.log(Status.INFO, result.getThrowable());
        String screenshotPath = takeScreenShot(testName);
        try {
            Test.addScreenCaptureFromPath(screenshotPath); // Add screenshot to Extent Report
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach screenshot to report", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        Test.log(Status.SKIP, testName + " Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
        File eReportFile = new File("C:\\Users\\cibhi\\eclipse-workspace\\DemoBlaze1\\report\\TestReport.html");
        try {
            Desktop.getDesktop().browse(eReportFile.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
