@usps
Feature: USPS scenarios

  @usps6
  Scenario: Validate ZIP code for old home, using POM
    Given I open "usps" page, using POM
    When I go to Lookup ZIP page by address, using POM
    And I fill out "2576 Harlow Lane" street, "San Ramon" city, "CA" state, using POM
    Then I validate "94582" zip code exists in the result, using POM

  @usps4
  Scenario: Calculate price logic page object
    Given I go to usps page object
    When I go to Calculate Price page object
    And I select "United Kingdom" with "Postcard" shape page object
    And I define "2" quantity page object
    Then I calculate the price and validate cost is "$2.80" page object

  @usps5
  Scenario: Wrong store id does not match page object
    Given I go to usps page object
    When I go to Postal Store tab
    And I enter "12345" into store search page object
    Then I search and validate no products found page object