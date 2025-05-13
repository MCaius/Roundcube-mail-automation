package com.testing.steps;

import com.testing.pages.MailboxPage;
import io.cucumber.java.en.*;
import static com.testing.context.ScenarioContext.getDriver;

public class MarkAsSpamSteps {
    private MailboxPage mailbox;

    @When("I mark the first email as spam")
    public void i_mark_the_first_email_as_spam() {
        mailbox = new MailboxPage(getDriver());
        mailbox.goToInbox();
        mailbox.clickFirstEmailInList();
        mailbox.storeEmailIdentifiers();
        mailbox.markAsSpam();
    }

    @Then("the email should appear in the Spam folder")
    public void the_email_should_appear_in_the_spam_folder() {
        mailbox.goToSpam();
        mailbox.assertEmailIdentifiers();
        mailbox.clickFirstEmailInList();
        mailbox.assertEmailIdentifiersFromFrame();
    }
}
