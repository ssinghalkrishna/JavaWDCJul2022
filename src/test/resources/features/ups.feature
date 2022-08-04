@ups
Feature: UPS test cases

  @ups1 @smoke
  Scenario: UPS end to end first
    Given I go to "ups_global" page (upload)
    And I select "North America" and "United States - English" on global page
    And I open Shipping menu
    When I go to Create Shipment page
    When I fill out origin shipment fields
    And I Submit the form
    When I cancel the form
    Then I verify shipment form is reset