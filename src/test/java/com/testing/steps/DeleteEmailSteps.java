package com.testing.steps;

import com.testing.pages.MailboxPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

import static com.testing.context.ScenarioContext.getDriver;

public class DeleteEmailSteps {
    private MailboxPage mailbox;

    @When("I delete the first email from the inbox")
    public void i_delete_the_first_email_from_the_inbox() {
        mailbox = new MailboxPage(getDriver());
        mailbox.clickFirstEmailInList();
        mailbox.storeEmailIdentifiers();
        mailbox.deleteEmail();
    }

    @Then("the email should appear in the Trash")
    public void the_email_should_appear_in_the_trash() {
        mailbox.goToTrash();
        mailbox.assertEmailIdentifiers();
        mailbox.clickFirstEmailInList();
        mailbox.assertEmailIdentifiersFromFrame();
    }

    @And("I permanently delete the email from the Trash")
    public void i_permanently_delete_the_email_from_the_trash() {
        mailbox.deleteEmail();
    }

    @Then("the Trash folder should be empty")
    public void the_trash_folder_should_be_empty() {
        Assert.assertTrue(mailbox.isEmailListEmpty(), "Trash folder is not empty");
    }
}