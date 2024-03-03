
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario: Title of your scenario
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  										| password 	|
      | naveenpractice7@gmail.com | Tes2t@1234 |
