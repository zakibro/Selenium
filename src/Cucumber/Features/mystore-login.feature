Feature: MyStore login

  Scenario Outline: User can login
    Given an open browser with my store login page
    When user enters valid "<email>" and "<password>"
    And submits the login form
    Then the user is logged on correct account with the "<accountName>"
    And quit browser

    Examples:
      | email                             | password | accountName           |
      | testowy159664076639163@onet.pl    | 4%wcl    | Tester Danielak       |
      | testowy159664076639160@yahoo.com  | ^4w%7    | Jakub Lewandowski     |
      | testowy159664076639190@gmail.com  | 1pp@d    | Michał Aleksandrowicz |
      | testowy159664076639146@yahoo.com  | ky&c^    | Paweł Mazur           |
      | testowy159664076639112@interia.pl | 8&x7k    | Michał Testowy        |


  Scenario Outline: User cannot login
    Given an open browser with my store login page
    When user enters invalid "<email>" or "<password>"
    And submits the login form
    Then the user is not logged in
    And sees the "Authentication failed."
    And quit browser

    Examples:
      | email                             | password      |
      | testowy159664076639163@onet.pl    | wrongPassword |
      | testowy159664076639160@yahooo.com | ^4w%7         |
