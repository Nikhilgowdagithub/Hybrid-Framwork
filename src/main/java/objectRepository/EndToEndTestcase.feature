 @tag
Feature: Purchase the order from Ecommerce Website
 
  
Background:
Given i landed on ecommers page

  @Reggresion
  Scenario Outline: Pasitive TC of submitting order
    Given login with username <name> and password <value>
    When i add product to cart<productName>
    And checkout Submit Order of product<productName> country<countryName>suggested<autosuggestCountryselect>
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
   
    Examples: 
      | name                    | value          |productName|countryName|autosuggestCountryselect|
      | nikhilgowda11@gmail.com | Nikhilgowda11@ |ZARA COAT 3| in        |india                   |