Feature: Create New Address after login

  Scenario Outline: user can create a new address
    Given user is logged in my store
    When user goes to AddressPage
    And user enters required fields with "<company>", "<address>", "<postcode>" and "<city>"
    And user saves information
    Then User sees "Address successfully added!"
    And quit the browser

    Examples:
    |company|address|postcode|city    |
    |Tesla  |Adres 1|1234    |Katowice|
    |Google |Adres 2|5433    |New York|
    |Netflix|Adres 3|5671    |Detroit |