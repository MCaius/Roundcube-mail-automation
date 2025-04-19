package com.testing.tests;

import com.testing.pages.MailboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MarkAsSpamTest extends TestBase {

    @Test(groups = "regression")
    public void markEmailAsSpamTest() {
        MailboxPage mailbox = new MailboxPage(driver);

        // Asser successful login
        Assert.assertTrue(mailbox.isLoginSuccessful(), "Login was not successful");

        // Go to Inbox tab
        mailbox.goToInbox();

        // Select the first email in the list
        mailbox.clickFirstEmailInList();

        // Store identifiers
        mailbox.storeEmailIdentifiers();

        // Click the Spam button.
        mailbox.markAsSpam();

        // Navigate to the "Spam" folder.
        mailbox.goToSpam();

        // Select the first email in the list
        mailbox.clickFirstEmailInList();

        // Assert email in Spam
        mailbox.assertEmailIdentifiers();

        // Reassert everything in IFrame match
        mailbox.assertEmailIdentifiersFromFrame();

        // Log off
        mailbox.logout();

    }
}
