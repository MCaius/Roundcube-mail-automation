Feature: Login to Roundcube

  Background:
    Given I am logged into Roundcube

  @smoke
  Scenario: Successful login
    Then I am on the inbox page