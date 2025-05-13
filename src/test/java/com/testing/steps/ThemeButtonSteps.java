package com.testing.steps;

import com.testing.pages.MailboxPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import static com.testing.context.ScenarioContext.getDriver;

public class ThemeButtonSteps {
    private MailboxPage mailbox;

    public ThemeButtonSteps() {
        this.mailbox = new MailboxPage(getDriver());
    }

    @When("^I select the \"([^\"]+)\" theme$")
    public void selectTheme(String themeName) {
        mailbox.toggleThemeIfNeeded(themeName);
    }

    @Then("^the \"([^\"]+)\" theme should be applied$")
    public void verifyThemeApplied(String themeName) {
        Assert.assertTrue(mailbox.isThemeApplied(themeName),
                "Expected theme to be applied: " + themeName);
    }
}
