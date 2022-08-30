@form
Feature: Sample form with Page Object Model

  @form1
  Scenario: Sample form Page Object fields
    Given I open sample page
    When I fill out sample fields
    And I submit sample form
    Then I verify all fields