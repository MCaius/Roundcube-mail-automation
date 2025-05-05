package com.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import com.testing.model.EmailMetadata;
import com.testing.model.ComposeEmail;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailboxPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(MailboxPage.class);

    public MailboxPage(WebDriver driver) {
        super(driver);
    }

    // ================== Mailbox ==================
    @FindBy(css = "a.compose#rcmbtn107")
    private WebElement composeButton;

    @FindBy(css = "a.mail#rcmbtn105")
    private WebElement backToMainDashboardButton;

    @FindBy(css = "li#rcmliSU5CT1g a")
    private WebElement inboxFolder;

    @FindBy(css = "li.drafts#rcmliSU5CT1guRHJhZnRz a")
    private WebElement draftsFolder;

    @FindBy(css = "li.sent#rcmliSU5CT1guU2VudA a")
    private WebElement sentFolder;

    @FindBy(css = "li#rcmliSU5CT1guVHJhc2g a")
    private WebElement trashFolder;

    @FindBy(css = "li.junk#rcmliSU5CT1guc3BhbQ a")
    private WebElement spamFolder;

    @FindBy(css = "a.logout#rcmbtn113")
    private WebElement logoutButton;

    // Mailbox components
    @FindBy(css = "table#messagelist tbody tr:first-of-type span.subject")
    private WebElement firstEmailInList;

    @FindBy(css = "tr.message td.subject span.fromto span span")
    private WebElement emailInEmilList;

    @FindBy(css = "tr.message td.subject span.subject a span")
    private WebElement subjectInEmilList;

    // Toolbar on top of frame
    @FindBy(css = "a.save.draft#rcmbtn111")
    private WebElement saveDraftButton;

    @FindBy(css = "a.delete#rcmbtn127")
    private WebElement deleteEmailButton;

    @FindBy(css = "a.junk#rcmbtn103")
    private WebElement markSpamButton;

    // 3rd column / Iframe components
     @FindBy(css = "a.rcmContactAddress")
     private WebElement fromEmailInFrame;

     @FindBy(css = "h2.subject")
     private WebElement subjectInFrame;

     @FindBy(css = "div#messagebody")
     private WebElement messageInFrame;

    @FindBy(css = "a.btn")
    private WebElement editDraftButton;

    @FindBy(css = "iframe#messagecontframe")
    private WebElement selectedEmailFrame;

    // ================== Compose Tab ==================
    @FindBy(css = "ul.recipient-input li input[type='text']")
    private WebElement toInput;

    @FindBy(css = "div#compose_subject input#compose-subject")
    private WebElement subjectInput;

    @FindBy(css = "textarea#composebody")
    private WebElement bodyInput;

    @FindBy(css = "button.send#rcmbtn115")
    private WebElement sendButton;


    // Store details for assertion
    private EmailMetadata storedEmailIdentifiers;

    // ================== for all tests / common ==================

    public boolean isLoginSuccessful() {
        boolean urlCheck = wait.until(ExpectedConditions.urlContains("_task=mail"));
        boolean inboxLoaded = webActions.isElementPresent(composeButton); // Compose button should be visible
        return urlCheck && inboxLoaded;
    }

    public void goToInbox() {
        enhancedActions.enhancedClick(inboxFolder);
    }

    public void clickFirstEmailInList() {
        enhancedActions.enhancedClick(firstEmailInList);
    }

    // Stores the subject and "to email"
    public void storeEmailIdentifiers() {
        WebElement senderElement = webActions.waitForVisibility(emailInEmilList);
        WebElement subjectElement = webActions.waitForVisibility(subjectInEmilList);

        String senderTitle = senderElement.getAttribute("title").trim();
        String subjectText = subjectElement.getText().trim();

        storedEmailIdentifiers = new EmailMetadata(senderTitle, subjectText);
        logger.info("📩 Stored email identifiers: {}", storedEmailIdentifiers);
    }

    // Asserts if the stored subject and "to email" match the currently clicked email's details
    public void assertEmailIdentifiers() {
        WebElement senderElement = webActions.waitForVisibility(emailInEmilList);
        WebElement subjectElement = webActions.waitForVisibility(subjectInEmilList);

        String currentSenderTitle = senderElement.getAttribute("title").trim();
        String currentSubjectText = subjectElement.getText().trim();

        EmailMetadata actualEmail = new EmailMetadata(currentSenderTitle, currentSubjectText);
        logger.info("📨 Actual email identifiers: {} {}", currentSenderTitle, currentSubjectText);

        Assert.assertEquals(actualEmail.getSender(), storedEmailIdentifiers.getSender(), "Sender title mismatch");
        Assert.assertEquals(actualEmail.getSubject(), storedEmailIdentifiers.getSubject(), "Subject mismatch");
    }

    // Asserts if the stored details match the currently opened email's in the iframe
    public void assertEmailIdentifiersFromFrame() {
        enhancedActions.switchToFrame(selectedEmailFrame);

        String senderFromFrame = webActions.waitForVisibility(fromEmailInFrame).getAttribute("title").trim();
        String subjectFromFrame = webActions.waitForVisibility(subjectInFrame).getText().trim();

        EmailMetadata frameEmail = new EmailMetadata(senderFromFrame, subjectFromFrame);

        logger.info("📨 Email from iframe: {}", frameEmail);

        Assert.assertEquals(frameEmail.getSender(), storedEmailIdentifiers.getSender(), "Sender mismatch (frame)");
        Assert.assertEquals(frameEmail.getSubject(), storedEmailIdentifiers.getSubject(), "Subject mismatch (frame)");

        enhancedActions.switchToDefaultContent();
    }


    public boolean isEmailListEmpty() {
        return !webActions.isElementPresent(firstEmailInList);
    }

    public void logout() {
        enhancedActions.enhancedClick(logoutButton);
        logger.info("=== Logged out ===");
    }

    public boolean isLogoutSuccessful() {
        return wait.until(ExpectedConditions.urlContains("webmaillogou"));
    }

    // ================== Compose | Draft | Send ==================

    public void goToCompose() {
        enhancedActions.enhancedClick(composeButton);
    }

    // Fill in "To", "Subject", and "Body"
    public void fillEmailFields(ComposeEmail email) {
        webActions.type(toInput, email.getTo());
        webActions.type(subjectInput, email.getSubject());
        webActions.type(bodyInput, email.getBody());
    }

    public void saveDraft() {
        enhancedActions.enhancedClick(saveDraftButton);
    }

    public void backToMainDashboard() {
        enhancedActions.enhancedClick(backToMainDashboardButton);
    }

    public void goToDrafts() {
        enhancedActions.enhancedClick(draftsFolder);
    }

    // Edit Draft to make changes and assertions
    public void editDraft() {
        enhancedActions.switchToFrame(selectedEmailFrame);
        enhancedActions.enhancedClick(editDraftButton);
        enhancedActions.switchToDefaultContent();
    }

    // Check details in the selected email iframe
    public boolean isSelectedEmailContentCorrect(ComposeEmail expectedEmail) {
        enhancedActions.switchToFrame(selectedEmailFrame);

        String actualTo = webActions.getText(fromEmailInFrame);
        String actualSubject = webActions.getText(subjectInFrame);
        String actualBody = webActions.getText(messageInFrame);

        enhancedActions.switchToDefaultContent();

        return actualTo.equals(expectedEmail.getTo()) &&
                actualSubject.equals(expectedEmail.getSubject()) &&
                actualBody.equals(expectedEmail.getBody());
    }

    public void sendEmail() {
        enhancedActions.enhancedClick(sendButton);
    }

    public void goToSent() {
        enhancedActions.enhancedClick(sentFolder);
    }

    // ================== Delete Email ==================
    public void deleteEmail() {
        enhancedActions.enhancedClick(deleteEmailButton);
    }

    public void goToTrash() {
        enhancedActions.enhancedClick(trashFolder);
    }

    // ================== Spam Email ==================
    public void markAsSpam() {
        enhancedActions.enhancedClick(markSpamButton);
    }

    public void goToSpam() {
        enhancedActions.enhancedClick(spamFolder);
    }
}
