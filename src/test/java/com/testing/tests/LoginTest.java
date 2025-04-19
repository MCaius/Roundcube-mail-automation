package com.testing.tests;

import com.testing.pages.MailboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test(groups = "smoke")
    public void verifySuccessfulLogin() {
        MailboxPage mailbox = new MailboxPage(driver);

        // Assert successful login (URL + UI check)
        Assert.assertTrue(mailbox.isLoginSuccessful(), "Login was not successful");

        // Optional: logout to clean up
        mailbox.logout();
    }
}
