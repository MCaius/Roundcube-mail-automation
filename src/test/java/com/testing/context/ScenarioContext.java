package com.testing.context;

import org.openqa.selenium.WebDriver;

/**
 * Holds a thread-local WebDriver instance for Cucumber scenarios.
 * Hooks set the driver before scenario execution, and step definitions
 * retrieve it via ScenarioContext.getDriver().
 */

public class ScenarioContext {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
