package com.testing.utils;

import com.epam.reportportal.service.ReportPortal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;

/**
 * Utility to take a screenshot with timestamp, save it locally, and optionally send it to ReportPortal.
 */
public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    /**
     * Takes a screenshot and saves it locally in the "screenshots" folder.
     * Returns the saved File object (or null if saving failed).
     */
    public static File takeScreenshot(WebDriver driver, String actionName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshotBytes = ts.getScreenshotAs(OutputType.BYTES);

            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String screenshotName = "failed_" + actionName + "_" + timestamp + ".png";
            File dest = new File(screenshotDir, screenshotName);

            File src = OutputType.FILE.convertFromPngBytes(screenshotBytes);
            FileHandler.copy(src, dest);

            logger.info("📸 Screenshot saved: {}", dest.getAbsolutePath());
            return dest;

        } catch (IOException | WebDriverException ex) {
            logger.error("❌ Failed to save screenshot: {}", ex.getMessage());
            return null;
        }
    }

    /**
     * Uploads a given screenshot file to ReportPortal with a custom message.
     */
    public static void uploadToReportPortal(File screenshotFile, String message) {
        if (screenshotFile == null || !screenshotFile.exists()) {
            logger.warn("⚠️ Screenshot file is missing or invalid — skipping upload");
            return;
        }

        try {
            ReportPortal.emitLog(message, "ERROR", new Date(), screenshotFile);
            logger.info("📤 Screenshot uploaded to ReportPortal: {}", screenshotFile.getName());
        } catch (Throwable e) { // Catch NoClassDefFoundError too
            logger.debug("📭 ReportPortal not available — skipping upload: {}", e.getMessage());
        }
    }

    /**
     * Finds the most recent screenshot file matching the given prefix.
     * Useful for test-level reporting.
     */
    public static File getLatestScreenshotFile(String prefix) {
        File dir = new File("screenshots");
        File[] matching = dir.listFiles((d, name) -> name.startsWith(prefix) && name.endsWith(".png"));

        if (matching == null || matching.length == 0) return null;

        return Arrays.stream(matching)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }
}
