Feature: As a user, I want to view detailed information about a book.

  Background: I am already logged in
    Given I am on bookstore login page
    When I enter credentials

    @bookSelection
    Scenario: I should be able to view information about a book
      Given I am in the bookstore
      When I select a book
      Then I am provided details regarding that book