@workday
Feature: Workday feature

  @workday1
  Scenario: WD scenario
    Given I go to "workday" page (upload)
    When I select any position
    And I Apply with LinkedIn
    Then I verify that valid OAuth LinkedIn login page opens