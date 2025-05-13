Feature: Mark Email as Spam

  Background:
    Given I am logged into Roundcube

  @regression
  Scenario: Mark the first email in the inbox as spam and verify it ends up in the Spam folder
    When I mark the first email as spam
    Then the email should appear in the Spam folder
    And I log out from Roundcube
