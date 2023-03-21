Feature: I should be able to search for a book using search box

  Background: I am already logged in
    Given I am on bookstore login page
    When I enter credentials
      @BookSearch
    Scenario: I should be returned the book I search for
      When I enter bookstore
      And  I search for a book
      Then I should see that book in the search results