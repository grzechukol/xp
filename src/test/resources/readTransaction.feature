Feature: Possibility to read a transaction.
  Scenario: The Transaction can be read
    Given I type read option
    When I click enter
    Then I see transactions
