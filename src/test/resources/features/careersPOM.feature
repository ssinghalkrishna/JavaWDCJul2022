@careers
Feature: Careers scenarios

  @careers2
  Scenario: candidate applies to a position
    Given I open "careers" page object
    And I apply to a new position
    Then I verify profile is created
    And I see position in my jobs


  @careers3
  Scenario: Candidate adds new job
    Given I open "careers" page object
    And I login as "candidate"
    Then I verify "candidate" login
    When I apply for a new job
    Then I see position marked as applied
    And I see position added in my jobs
