Feature: As a user, I want to sort and filter the book list

  Background: I am already logged in
    Given I am on bookstore login page
    When I enter credentials

    @dynamicSearch
    Scenario: When I use the search bar, I want it to dynamically search the books list
      Given I am in the bookstore
      When I enter keys into the search bar
      Then the book selection should dynamically filter