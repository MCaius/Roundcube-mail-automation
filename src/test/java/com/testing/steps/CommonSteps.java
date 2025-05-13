package com.testing.steps;

import com.testing.driver.DriverSingleton;
import com.testing.pages.LoginPage;
import com.testing.pages.MailboxPage;
import com.testing.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private MailboxPage mailbox;

    @Given("I open the Roundcube login page")
    public void openLoginPage() {
        driver = DriverSingleton.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @Given("I am logged into Roundcube")
    public void i_am_logged_into_roundcube() {
        // Open the page
        openLoginPage();
        // Perform login
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
        // Verify inbox
        mailbox = new MailboxPage(driver);
        Assert.assertTrue(
                mailbox.isLoginSuccessful(),
                "Expected to land on the inbox page after login"
        );
    }

    @Then("I am on the inbox page")
    public void i_am_on_the_inbox_page() {
        Assert.assertTrue(mailbox.isLoginSuccessful());
    }

    @When("I log out from Roundcube")
    public void i_log_out_from_roundcube() {
        // mailbox should already be initialized in a previous step
        if (mailbox == null) {
            mailbox = new MailboxPage(driver);
        }
        mailbox.logout();
    }

    @Then("I should see the login page")
    public void i_should_see_the_login_page() {
        // Check that the email input is present on the login page
        Assert.assertTrue(
                loginPage.isAt(),
                "Expected to be on the login page after logout"
        );
    }
}

