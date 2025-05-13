package com.testing.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.testing.steps", "com.testing.hooks"},
        tags = "@regression",
        plugin = {
                "pretty",
                "html:target/cucumber-report/firefox.html",
                "json:target/cucumber-report/firefox.json"
        }
)
public class CucumberFirefoxRegressionRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        System.setProperty("browser", "firefox");
        return super.scenarios();
    }
}
