package com.testing.listeners;

import com.testing.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

/**
 * TestNG listener for automatic screenshot capture on test failure.
 * When a test fails, it captures a screenshot and uploads it to ReportPortal.
 */
public class ScreenshotTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        try {
            // Dynamically get the WebDriver from the test instance
            WebDriver driver = (WebDriver) testInstance
                    .getClass()
                    .getMethod("getDriver")
                    .invoke(testInstance);

            String testName = result.getName();

            // Take the screenshot and save it locally
            File screenshot = ScreenshotUtil.takeScreenshot(driver, testName);

            // Upload screenshot to ReportPortal
            ScreenshotUtil.uploadToReportPortal(screenshot, "📎 Screenshot on failure: " + testName);

        } catch (Exception e) {
            // Log any reflection or driver-related errors
            e.printStackTrace();
        }
    }
}
