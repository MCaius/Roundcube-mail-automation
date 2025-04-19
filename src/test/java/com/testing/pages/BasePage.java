package com.testing.pages;

import com.testing.utils.EnhancedWebActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected EnhancedWebActions enhancedActions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.enhancedActions = new EnhancedWebActions(driver, this.wait);

        // Initialize PageFactory for all subclasses
        PageFactory.initElements(driver, this);
    }

    // Utility methods for working with WebElements
    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element).clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return waitForVisibility(element).getText().trim();
    }
}
