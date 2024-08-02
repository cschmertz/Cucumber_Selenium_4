# language: en
@AUTOMATED @Google @EL-19 @EL-22
Feature: Google test

    Scenario: Testing Google
        
        Given I navigate to Google search page
        When I check page title
        Then I have page title assertion

