Feature: OrangeHRM Login
  Scenario: Logo presence on Orange HRM home Page
    Given I launch chrome browse
    When I open orange hrm homepage
    Then I verify that the logo present on page
    And close browser