@BookList
Feature: A list of books should be available

  Background: I am logged into the bookstore
    Given I am on bookstore login page
    When I enter credentials

    Scenario: I should be able to see a a list of books available
      Given I am on profile page
      When  I enter the bookstore
      Then I should be able to see all the books available