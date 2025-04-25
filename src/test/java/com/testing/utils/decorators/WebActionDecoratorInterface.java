package com.testing.utils.decorators;

import org.openqa.selenium.WebElement;

/*
 * Interface for web interaction decorators.
 * This defines enhanced web actions from EnhancedWebAction.java.
 * Classes like ScreenshotOnFailureDecorator use this to wrap standard behavior with extras.
 */

public interface WebActionDecoratorInterface {
    void enhancedClick(WebElement element);
    void highlightElement(WebElement element);
    void switchToFrame(WebElement iframeElement);
    void switchToDefaultContent();
    void shortWait(long millis);
}
