package com.testing.tests;

import com.testing.pages.LoginPage;
import com.testing.pages.MailboxPage;
import com.testing.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test(groups = "smoke")
    public void verifySuccessfulLogin() {
        // 1) Perform login explicitly
        new LoginPage(driver).login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        // 2) Navigate to mailbox page object
        MailboxPage mailbox = new MailboxPage(driver);

        // 3) Assert successful login (URL + UI check)
        Assert.assertTrue(
                mailbox.isLoginSuccessful(),
                "Login was not successful"
        );

        // 4) Optional: logout to clean up
        mailbox.logout();
    }
}
