package com.testing.hooks;

import com.testing.driver.DriverSingleton;
import com.testing.utils.ConfigReader;
import com.testing.utils.EnvironmentManager;
import com.testing.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static com.testing.context.ScenarioContext.setDriver;

/**
 * Cucumber hooks to manage browser lifecycle around each scenario.
 * - @Before: spins up WebDriver, navigates to base URL, and saves it in ScenarioContext.
 * - @After: quits the browser.
 */

public class CucumberHooks {
    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);

    @Before
    public void setUp() {
        logger.info("[Cucumber @Before] Starting scenario in environment: {}", EnvironmentManager.getEnv());

        WebDriver driver = DriverSingleton.getDriver();
        driver.get(ConfigReader.get("baseUrl"));

        setDriver(driver);  // Save to thread-safe context (see step 2)
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverSingleton.getDriver();

        // Take screenshot on failure
        if (scenario.isFailed()) {
            File screenshot = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
            ScreenshotUtil.uploadToReportPortal(screenshot, "🧨 Cucumber failure screenshot: " + scenario.getName());
        }

        // Quit driver after every scenario
        DriverSingleton.closeDriver();
        logger.info("[Cucumber @After] Quitting driver");
    }
}
