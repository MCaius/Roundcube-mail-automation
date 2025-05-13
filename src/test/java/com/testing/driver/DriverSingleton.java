package com.testing.driver;

import org.openqa.selenium.WebDriver;

/**
 * Singleton pattern implementation for WebDriver management.
 * Ensures a single instance of WebDriver is used across tests to avoid conflicts.
 * Handles both driver instantiation and cleanup.
 */

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
        // Prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            WebDriver driver = DriverFactory.createDriver(browser);
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // Clean up ThreadLocal
        }
    }
}

