package com.testing.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.testing.steps", "com.testing.hooks"},
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-report/chrome.html",
                "json:target/cucumber-report/chrome.json"
        }
)
public class CucumberChromeSmokeRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        System.setProperty("browser", "chrome"); // or use an EnvironmentManager
        return super.scenarios();
    }
}