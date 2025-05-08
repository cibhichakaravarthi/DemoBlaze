package Listerners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterGenerator {

    public static ExtentReports getExtentReport() {
        String reportPath = "C:\\Users\\cibhi\\eclipse-workspace\\DemoBlaze1\\report\\TestReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("DemoBlaze Test Report");
        reporter.config().setDocumentTitle("Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Cibhi");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");

        return extent;
    }
}
