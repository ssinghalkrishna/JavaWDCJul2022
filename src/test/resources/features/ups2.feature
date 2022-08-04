@ups2
Feature: UPS second feature

  @ups2-1 @regression
  Scenario: UPS end to end full
    Given I go to "ups" page (upload)
    And I open Shipping menu
    When I go to Create Shipment page
    When I fill out origin shipment fields
    And I Submit the form
    Then I verify origin shipment fields submitted
    When I fill out destination shipment info
    And I Submit the form
    And I set the packaging type
    And I Submit the form
    Then I verify total charges display
    And I select the cheapest delivery option
    #Then I verify that total charges changed
    And I Submit the form
    And I enter Contents
    And I Submit the form
    And I select Paypal payment type
    And I Submit the form
    Then I review all recorded details on the review page
    And I cancel the form
    Then I verify shipment form is reset