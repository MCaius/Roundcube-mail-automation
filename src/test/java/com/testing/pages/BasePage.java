package com.testing.pages;

import com.testing.utils.EnhancedWebAction;
import com.testing.utils.decorators.PerformanceTimerDecorator;
import com.testing.utils.decorators.ScreenshotOnFailureDecorator;
import com.testing.utils.decorators.WebActionDecoratorInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.testing.utils.WebActions;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebActionDecoratorInterface enhancedActions;
    protected WebActions webActions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.enhancedActions = new ScreenshotOnFailureDecorator(
                new PerformanceTimerDecorator(
                        new EnhancedWebAction(driver, this.wait)
                ),
                driver
        );

        this.webActions = new WebActions(this.wait);

        // Initialize PageFactory for all subclasses
        PageFactory.initElements(driver, this);
    }
}
