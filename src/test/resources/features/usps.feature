@usps
  Feature: USPS feature

    @usps1
    Scenario: verify zipcode from address
      Given I open "usps" page
      And I go to Lookup ZIP page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      And I search for result
      Then result must contain "94022" zip code

    @usps2
    Scenario: Validate ZIP code
      Given I open "usps" page
      When I mouse over and go to Lookup Zip page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      And I search for result
      Then result must contain "94022" zip code

    @usps3
    Scenario: Validate ZIP code with Calculate price
      Given I open "usps" page
      When I go to Calculate price page
      And I put country, shape, quantity
      And I submit for result
      Then result must have price "$2.80"

