package com.testing.tests;

import com.testing.driver.DriverSingleton;
import com.testing.pages.LoginPage;
import com.testing.utils.ConfigReader;
import com.testing.utils.EnvironmentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Register Listener
@Listeners(com.testing.listeners.ScreenshotTestListener.class)
public class TestBase {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    // Runs before each test method — sets up the browser, opens the URL, and logs in
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("[BeforeMethod] Running tests in environment: {}", EnvironmentManager.getEnv());

        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Load base URL from config
        String baseUrl = ConfigReader.get("baseUrl");
        logger.info("[BeforeMethod] Navigating to: {}", baseUrl);

        // If baseUrl is null, this will throw the exception
        driver.get(baseUrl);

        // Login using credentials from config
        new LoginPage(driver).login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    // Runs after each test — shuts down the browser
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Quit driver
        DriverSingleton.closeDriver();
    }

    // Expose diriver for listeners
    public WebDriver getDriver() {
        return driver;
    }

}
