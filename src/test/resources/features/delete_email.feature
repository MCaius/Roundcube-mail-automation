Feature: Delete Email

  Background:
    Given I am logged into Roundcube

  Scenario: Successfully delete an email and confirm it's removed
    Given I am on the inbox page
    When I delete the first email from the inbox
    Then the email should appear in the Trash
    And I permanently delete the email from the Trash
    Then the Trash folder should be empty
    And I log out from Roundcube
