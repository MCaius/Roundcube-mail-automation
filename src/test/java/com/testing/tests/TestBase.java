package com.testing.tests;

import com.testing.driver.DriverSingleton;
import com.testing.pages.LoginPage;
import com.testing.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestBase {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    // Runs ONCE when this class is first loaded — before any @BeforeMethod or @Test
    static {
        // Get 'env' from system properties (e.g., -Denv=staging), fallback to 'staging' if not provided
        String env = System.getProperty("env", "staging");

        // Load the corresponding config file (e.g., config.staging.properties)
        logger.debug("🛠 Loading config for env: {}", env);
        ConfigReader.loadConfig(env);
    }

    // Runs before each test method — sets up the browser, opens the URL, and logs in
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
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
    public void tearDown(ITestResult result) {
        // Check if test failed
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);

                // Create folder if it doesn't exist
                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdirs(); // create the folder
                }

                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String testName = result.getName();
                String screenshotName = "failed_" + testName + "_" + timestamp + ".png";
                String filePath = "screenshots/" + screenshotName;

                File dest = new File(filePath);
                FileHandler.copy(src, dest);

                logger.info("📸 Screenshot saved: {}", dest.getAbsolutePath());

            } catch (IOException e) {
                logger.error("❌ Failed to save screenshot: {}", e.getMessage());
            }
        }

        // Quit driver
        DriverSingleton.closeDriver();
    }

}


