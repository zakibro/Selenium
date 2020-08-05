Feature: Qlo register

  Scenario Outline: user can register a new account
    Given the user is on Qlo register page
    When user enters valid  "<email>" address which hasn't been used
    And user enters the valid : "<firstName>", "<lastName>" and "<password>"
    Then the account has been created
    And user is logged in and sees name "<firstName>"

    Examples:
      | email            | firstName | lastName | password    |
      | pierwy23ts@o2.pl | Pawel     | Mazur    | password123 |
      | druges23@net.pl  | Kamil     | Kowalski | passwd456   |
      | trzc23t@gmil.com | Robert    | Nowak    | haslo12345  |