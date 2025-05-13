package com.testing.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG runner for Cucumber features.
 * - Scans feature files under src/test/resources/features.
 * - Looks for step definitions and hooks in the specified glue packages.
 * - Generates pretty console output plus HTML and JSON reports.
 */

@CucumberOptions(
        features = "src/test/resources/features",
        glue     = {
                "com.testing.steps",     // your step defs
                "com.testing.hooks"      // your Cucumber hooks
        },
        //tags = "@logIn", // Only run this tag
        plugin   = {"pretty", "html:target/cucumber-html-report.html",
                "json:target/cucumber.json"}
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

    // Ensures parallel execution if needed
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

