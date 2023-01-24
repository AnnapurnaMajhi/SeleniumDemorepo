
Feature: Application Login
  I want to use this template for my feature file



  Scenario: Login with valid credentials 
    Given Open any browser
    And User navigate to the login page
    When User enters username as "annapurnamajhi3@gmail.com" and password as "majhi@345"
    And user clicks on Login button
    Then Verify user is able to successfully login
