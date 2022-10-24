
@tag
Feature: error validation
 
  @errorvalidation
  Scenario Outline: check for negative scenario
   Given i landed on ecommers page
    When login with username <name> and password <value>
    Then "Incorrect email or password." error message is displayed

    Examples: 
      | name                          |       value         |
      | nikhilgowda111@gmail.com      |  Nikhilgowda11@     |
      