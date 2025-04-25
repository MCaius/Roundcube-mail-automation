package com.testing.utils.decorators;
import org.openqa.selenium.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

/*
 * Decorator that wraps web actions and captures a screenshot whenever an exception occurs.
 * Useful for debugging failed tests or visually tracking broken element interactions.
 */

public class ScreenshotOnFailureDecorator implements WebActionDecoratorInterface {

    private final WebActionDecoratorInterface wrapped;
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ScreenshotOnFailureDecorator.class);

    public ScreenshotOnFailureDecorator(WebActionDecoratorInterface wrapped, WebDriver driver) {
        this.wrapped = wrapped;
        this.driver = driver;
    }

    @Override
    public void enhancedClick(WebElement element) {
        try {
            wrapped.enhancedClick(element);
        } catch (Exception e) {
            takeScreenshot("enhancedClick");
            throw e;
        }
    }

    @Override
    public void highlightElement(WebElement element) {
        try {
            wrapped.highlightElement(element);
        } catch (Exception e) {
            takeScreenshot("highlightElement");
            throw e;
        }
    }

    @Override
    public void switchToFrame(WebElement iframeElement) {
        try {
            wrapped.switchToFrame(iframeElement);
        } catch (Exception e) {
            takeScreenshot("switchToFrame");
            throw e;
        }
    }

    @Override
    public void switchToDefaultContent() {
        try {
            wrapped.switchToDefaultContent();
        } catch (Exception e) {
            takeScreenshot("switchToDefaultContent");
            throw e;
        }
    }

    @Override
    public void shortWait(long millis) {
        try {
            wrapped.shortWait(millis);
        } catch (Exception e) {
            takeScreenshot("shortWait");
            throw e;
        }
    }

    // Utility to take a screenshot with timestamp
    private void takeScreenshot(String actionName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String screenshotName = "failed_" + actionName + "_" + timestamp + ".png";
            File dest = new File(screenshotDir, screenshotName);

            FileHandler.copy(src, dest);
            logger.info("📸 Screenshot saved: {}", dest.getAbsolutePath());

        } catch (IOException ex) {
            logger.error("❌ Failed to save screenshot: {}", ex.getMessage());
        }
    }
}
