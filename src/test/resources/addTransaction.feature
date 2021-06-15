Feature: Possibility to add a transaction.
  Scenario: The Transaction can be added
    Given I type add option
    When I add transaction values
    Then They are saved in the file
