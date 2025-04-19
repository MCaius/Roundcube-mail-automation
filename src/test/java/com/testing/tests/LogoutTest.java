package com.testing.tests;

import com.testing.pages.MailboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends TestBase {

    @Test(groups = "smoke")
    public void verifySuccessfulLogout() {
        MailboxPage mailbox = new MailboxPage(driver);

        // Assert successful login (URL + UI check)
        Assert.assertTrue(mailbox.isLoginSuccessful(), "Login was not successful");

        // Log off
        mailbox.logout();

        // Assert successful logout (URL check)
        Assert.assertTrue(mailbox.isLogoutSuccessful(), "Logout was not successful");
    }
}
