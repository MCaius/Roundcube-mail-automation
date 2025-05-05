package com.testing.driver;

import org.openqa.selenium.WebDriver;

/**
 * Singleton pattern implementation for WebDriver management.
 * Ensures a single instance of WebDriver is used across tests to avoid conflicts.
 * Handles both driver instantiation and cleanup.
 */

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
        // Ensures this class cannot be instantiated from outside
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            driver = DriverFactory.createDriver(browser);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Ensure driver reference is cleared after quitting
        }
    }
}
