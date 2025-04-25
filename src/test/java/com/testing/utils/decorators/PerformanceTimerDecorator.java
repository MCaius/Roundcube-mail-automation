package com.testing.utils.decorators;

import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Decorator that logs the execution time of each WebAction.
 * Wraps around any EnhancedWebActions method to measure how long each UI interaction takes.
 */

public class PerformanceTimerDecorator implements WebActionDecoratorInterface {

    private static final Logger logger = LogManager.getLogger(PerformanceTimerDecorator.class);
    private final WebActionDecoratorInterface wrapped;

    public PerformanceTimerDecorator(WebActionDecoratorInterface wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void enhancedClick(WebElement element) {
        long start = System.currentTimeMillis();
        wrapped.enhancedClick(element);
        logDuration("enhancedClick", start);
    }

    @Override
    public void highlightElement(WebElement element) {
        long start = System.currentTimeMillis();
        wrapped.highlightElement(element);
        logDuration("highlightElement", start);
    }

    @Override
    public void switchToFrame(WebElement iframeElement) {
        long start = System.currentTimeMillis();
        wrapped.switchToFrame(iframeElement);
        logDuration("switchToFrame", start);
    }

    @Override
    public void switchToDefaultContent() {
        long start = System.currentTimeMillis();
        wrapped.switchToDefaultContent();
        logDuration("switchToDefaultContent", start);
    }

    @Override
    public void shortWait(long millis) {
        long start = System.currentTimeMillis();
        wrapped.shortWait(millis);
        logDuration("shortWait", start);
    }

    private void logDuration(String action, long start) {
        long duration = System.currentTimeMillis() - start;
        logger.info("⏱️ Action '{}' took {} ms", action, duration);
    }
}
