Feature: Qlo register

  Scenario Outline: user can register a new account
    Given the user is on Qlo register page
    When user enters valid  "<email>" address which hasn't been used
    And user enters the valid : "<firstName>", "<lastName>" and "<password>"
    Then the account has been created
    And user is logged in and sees name "<firstName>"

    Examples:
      | email            | firstName | lastName | password    |
      | pierwyts@o2.pl | Pawel     | Mazur    | password123 |
      | druges@net.pl  | Kamil     | Kowalski | passwd456   |
      | trzct@gmil.com | Robert    | Nowak    | haslo12345  |