package com.testing.tests;

import com.testing.pages.MailboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteEmailTest extends TestBase {

    @Test(groups = "regression")
    public void deleteEmailTest() {
        MailboxPage mailbox = new MailboxPage(driver);

        // Asser successful login
        Assert.assertTrue(mailbox.isLoginSuccessful(), "Login was not successful");

        // Click to Inbox tab
        mailbox.goToInbox();

        // Select the first email in the list
        mailbox.clickFirstEmailInList();

        // Store identifiers
        mailbox.storeEmailIdentifiers();

        // Click the delete/trash icon.
        mailbox.deleteEmail();

        // Navigate to the "Trash" folder.
        mailbox.goToTrash();

        // Assert email in Trash
        mailbox.assertEmailIdentifiers();

        // Select the First email from trash
        mailbox.clickFirstEmailInList();

        // Reassert everything in iframe match
        mailbox.assertEmailIdentifiersFromFrame();

        // Permanently delete the email from Trash.
        mailbox.deleteEmail();

        // Make sure the Trash is empty
        Assert.assertTrue(mailbox.isEmailListEmpty(), "Trash folder is not empty");

        // Log off
        mailbox.logout();
    }

}
