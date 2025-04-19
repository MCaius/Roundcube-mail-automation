package com.testing.tests;

import com.testing.model.ComposeEmail;
import com.testing.pages.MailboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendDraftMailTest extends TestBase {

    @Test(groups = "regression")
    public void sendDraftEmailTest() {
        MailboxPage mailbox = new MailboxPage(driver);

        String to = "test-email@yahoo.ro";
        String subject = "Test Subject";
        String body = "Test Body";

        // Assert successful login
        Assert.assertTrue(mailbox.isLoginSuccessful(), "Login was not successful");

        // Navigate to Compose tab
        mailbox.goToCompose();

        // Create email. Fill in "To", "Subject", and "Body"
        ComposeEmail draft = new ComposeEmail(to, subject, body);
        mailbox.fillEmailFields(draft);

        // Save the email as a draft
        mailbox.saveDraft();

        // Go back to the Mailbox
        mailbox.backToMainDashboard();

        // Navigate to Drafts folder
        mailbox.goToDrafts();

        // Open the first draft
        mailbox.clickFirstEmailInList();

        // Store identifiers
        mailbox.storeEmailIdentifiers();

        // Assert email in Trash
        mailbox.assertEmailIdentifiers();

        // Verify the draft content Directly in Iframe
        Assert.assertTrue(mailbox.isSelectedEmailContentCorrect(draft), "Draft content does not match");

        // Click "edit" so we can send it
        mailbox.editDraft();

        // Send the email
        mailbox.sendEmail();

        // Make sure Draft folder is empty
        Assert.assertTrue(mailbox.isEmailListEmpty(), "Drafts folder is not empty");

        // Navigate to Sent folder
        mailbox.goToSent();

        // Verify the email is in Sent
        mailbox.assertEmailIdentifiers();

        // Log off
        mailbox.logout();
    }
}
