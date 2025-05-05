package com.testing.utils.decorators;

import org.openqa.selenium.*;
import com.testing.utils.ScreenshotUtil;
import java.io.File;

/**
 * Decorator that wraps web actions and captures a screenshot whenever an exception occurs.
 * Useful for debugging failed tests or visually tracking broken element interactions.
 */

public class ScreenshotOnFailureDecorator implements WebActionDecoratorInterface {

    private final WebActionDecoratorInterface wrapped;
    private final WebDriver driver;

    public ScreenshotOnFailureDecorator(WebActionDecoratorInterface wrapped, WebDriver driver) {
        this.wrapped = wrapped;
        this.driver = driver;
    }

    @Override
    public void enhancedClick(WebElement element) {
        try {
            wrapped.enhancedClick(element);
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "enhancedClick");

            File file = ScreenshotUtil.getLatestScreenshotFile("failed_enhancedClick");
            if (file != null) {
                ScreenshotUtil.uploadToReportPortal(file, "Screenshot on failure: enhancedClick");
            }

            throw e;
        }
    }

    @Override
    public void highlightElement(WebElement element) {
        try {
            wrapped.highlightElement(element);
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "highlightElement");

            File file = ScreenshotUtil.getLatestScreenshotFile("failed_enhancedClick");
            if (file != null) {
                ScreenshotUtil.uploadToReportPortal(file, "Screenshot on failure: enhancedClick");
            }

            throw e;
        }
    }

    @Override
    public void switchToFrame(WebElement iframeElement) {
        try {
            wrapped.switchToFrame(iframeElement);
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "switchToFrame");

            File file = ScreenshotUtil.getLatestScreenshotFile("failed_enhancedClick");
            if (file != null) {
                ScreenshotUtil.uploadToReportPortal(file, "Screenshot on failure: enhancedClick");
            }

            throw e;
        }
    }

    @Override
    public void switchToDefaultContent() {
        try {
            wrapped.switchToDefaultContent();
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "switchToDefaultContent");

            File file = ScreenshotUtil.getLatestScreenshotFile("failed_enhancedClick");
            if (file != null) {
                ScreenshotUtil.uploadToReportPortal(file, "Screenshot on failure: enhancedClick");
            }

            throw e;
        }
    }

    @Override
    public void shortWait(long millis) {
        try {
            wrapped.shortWait(millis);
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(driver, "shortWait");

            File file = ScreenshotUtil.getLatestScreenshotFile("failed_enhancedClick");
            if (file != null) {
                ScreenshotUtil.uploadToReportPortal(file, "Screenshot on failure: enhancedClick");
            }

            throw e;
        }
    }
}
