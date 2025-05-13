package com.testing.steps;

import com.testing.model.ComposeEmail;
import com.testing.pages.MailboxPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import static com.testing.context.ScenarioContext.getDriver;

public class SendDraftMailSteps {
    private MailboxPage mailbox;
    private ComposeEmail draft;

    @When("I compose a new draft email with to {string}, subject {string}, and body {string}")
    public void i_compose_a_new_draft_email(String to, String subject, String body) {
        mailbox = new MailboxPage(getDriver());
        mailbox.goToCompose();
        draft = new ComposeEmail(to, subject, body);
        mailbox.fillEmailFields(draft);
    }

    @And("I save the email as a draft")
    public void i_save_the_email_as_a_draft() {
        mailbox.saveDraft();
    }

    @And("I open the drafts folder and verify the draft content matches")
    public void i_open_the_drafts_folder_and_verify_the_draft_content() {
        mailbox.backToMainDashboard();
        mailbox.goToDrafts();
        mailbox.clickFirstEmailInList();
        mailbox.storeEmailIdentifiers();
        mailbox.assertEmailIdentifiers();
        Assert.assertTrue(
                mailbox.isSelectedEmailContentCorrect(draft),
                "Draft content does not match expected"
        );
    }

    @And("I send the draft email")
    public void i_send_the_draft_email() {
        mailbox.editDraft();
        mailbox.sendEmail();
    }

    @Then("the drafts folder should be empty")
    public void the_drafts_folder_should_be_empty() {
        mailbox.goToDrafts();
        Assert.assertTrue(
                mailbox.isEmailListEmpty(),
                "Expected no emails in drafts folder after sending"
        );
    }

    @And("the email should appear in the Sent folder")
    public void the_email_should_appear_in_the_sent_folder() {
        mailbox.goToSent();
        mailbox.assertEmailIdentifiers();
    }
}
