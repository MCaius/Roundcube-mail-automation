Feature: Compose, save, and send a draft email

  Background:
    Given I am logged into Roundcube

  @regression
  Scenario Outline: Save an email as draft, edit it, send it, and verify delivery
    When I compose a new draft email with to "<to>", subject "<subject>", and body "<body>"
    And I save the email as a draft
    And I open the drafts folder and verify the draft content matches
    And I send the draft email
    Then the drafts folder should be empty
    And the email should appear in the Sent folder
    And I log out from Roundcube

    Examples:
      | to                      | subject       | body        |
      | test-email@yahoo.com    | Hello 1       | Body text 1 |
      | test-email2@yahoo.com   | Greetings 2   | Body text 2 |