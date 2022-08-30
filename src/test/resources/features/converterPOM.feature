@converter1
Feature: Converter function, advanced

  @converter1_1
  Scenario Outline: Converter using Scenario Outline
    Given I go to "converter" page (upload)
    When I click on "<tab>"
    And I set "<from_unit>" to "<to_unit>"
    Then I enter into From field "<from_value>" and verify "<expected_value>" result
    Examples:
      | tab         | from_unit  | to_unit   | from_value | expected_value |
      | Temperature | Fahrenheit | Celsius   | 54         | 12.2           |
      | Weight      | Pound      | Kilogram  | 170        | 77             |
      | Length      | Mile       | Kilometer | 50         | 80.4           |


  @converter1_2
  Scenario Outline: Converter Page Object Model using Scenario Outline
    Given I open converter page
    When I click on "<tab>" on page
    And I set "<from_unit>" to "<to_unit>" on page
    Then I enter into From field "<from_value>" and verify "<expected_value>" result on page
    Examples:
      | tab         | from_unit  | to_unit   | from_value | expected_value |
      | Temperature | Fahrenheit | Celsius   | 54         | 12.2           |
      | Weight      | Pound      | Kilogram  | 170        | 77             |
      | Length      | Mile       | Kilometer | 50         | 80.4           |

