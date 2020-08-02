Feature: Qlo register

  Scenario: user can register a new account
    Given the user is on Qlo register page
    When user enters valid email address which hasn't been used
    And user enters the valid registration details
    Then the account has been created
    And user is logged in
