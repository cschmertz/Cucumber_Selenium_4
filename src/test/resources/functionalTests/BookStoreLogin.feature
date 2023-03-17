Feature: I should be able to login to the bookstore

  @Login
  Scenario: I should be able to login to bookstore with positive credentials
    Given I am on bookstore login page
    When I enter credentials
    Then I should be logged in