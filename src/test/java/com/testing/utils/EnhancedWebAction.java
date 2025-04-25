package com.testing.utils;

import com.testing.utils.decorators.WebActionDecoratorInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Provides enhanced interaction with web elements, such as safely clicking, highlighting,
 * switching frames, introducing controlled waits, etc. Can be used directly or wrapped
 * by decorators like ScreenshotOnFailureDecorator.
 */

public class EnhancedWebAction implements WebActionDecoratorInterface {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private static final Logger logger = LogManager.getLogger(EnhancedWebAction.class);


    // Notification container locator
    private final By notificationContainer = By.cssSelector("div#messagestack");

    public EnhancedWebAction(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
    }

    public void shortWait(long millis) {
        try {
            new WebDriverWait(driver, Duration.ofMillis(millis)).until(d -> true);
        } catch (Exception ignored) {
            logger.error("Short wait interrupted or failed.");
        }
    }

    // ======== WebElement-based enhanced click (PageFactory support) ========
    public void enhancedClick(WebElement element) {
        try {
            // 1. Wait for any notification to disappear
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationContainer));
            } catch (TimeoutException e) {
                logger.error("Notification did not disappear in time. Continuing anyway.");
            }

            // 2. Wait for the element to be visible and clickable
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // 3. Scroll it into view to avoid off-screen click errors
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'nearest'});", element);
            shortWait(300);

            // 4. Move to the element to hover it (some UIs load things on hover)
            actions.moveToElement(element).perform();
            shortWait(300);

            // 5. Highlight the element before the click
            highlightElement(element);

            // 6. Try clicking - with JS fallback
            try {
                element.click();
            } catch (Exception e) {
                logger.info("Fallback to JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }

            shortWait(300); // post-click pause
            logger.info("✅ Enhanced click on WebElement: {}", elementDescription(element));

        } catch (Exception e) {
            logger.error("❌ Failed to perform enhanced click", e);
            throw e; // 👈 THIS is the fix — rethrow the error
        }
    }

    // Utility for WebElement description
    private String elementDescription(WebElement element) {
        try {
            return element.getTagName() + (element.getText().isBlank() ? "" : " with text: \"" + element.getText().trim() + "\"");
        } catch (Exception e) {
            return "Unknown element";
        }
    }

    // Iframe actions
    public void switchToFrame(WebElement iframeElement) {
        WebElement iframe = wait.until(ExpectedConditions.visibilityOf(iframeElement));
        driver.switchTo().frame(iframe);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Highlight elements during test execution
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Save original style
        String originalStyle = element.getAttribute("style");

        // Apply highlight style
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: 3px solid red !important; " +
                        "background-color: rgba(237,240,0,0.6) !important");

        // Visible flash of highlight
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Restore original style
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                originalStyle);
    }

    // Add more enhanced actions here
}
