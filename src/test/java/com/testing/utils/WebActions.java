package com.testing.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * A collection of common, low-level web interaction methods such as typing into fields,
 * waiting for elements, or retrieving text. These are generic utilities meant to simplify
 * interaction with web elements.
 */

public class WebActions {

    private final WebDriverWait wait;

    public WebActions(WebDriverWait wait) {
        this.wait = wait;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void type(WebElement element, String text) {
        WebElement visible = waitForVisibility(element);
        visible.clear();
        visible.sendKeys(text);
    }

    public String getText(WebElement element) {
        return waitForVisibility(element).getText().trim();
    }
}
