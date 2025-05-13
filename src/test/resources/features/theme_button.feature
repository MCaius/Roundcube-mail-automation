Feature: Roundcube theme selection

  Background:
    Given I am logged into Roundcube

  @smoke
  Scenario: User changes the theme
    When I select the "Dark" theme
    Then the "Dark" theme should be applied
