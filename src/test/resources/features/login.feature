Feature: Login to Roundcube

  Background:
    Given I am logged into Roundcube

  @logIn
  Scenario: Successful login
    Then I am on the inbox page