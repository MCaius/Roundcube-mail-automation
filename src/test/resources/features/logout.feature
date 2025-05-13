Feature: Logout

  Background:
    Given I am logged into Roundcube

  Scenario: User logs out successfully
    When I log out from Roundcube
    Then I should see the login page
