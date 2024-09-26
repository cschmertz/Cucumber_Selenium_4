@ShadowDom @Suite
Feature: Shadow DOM Text Verification

  Scenario: Verify text in shadow element
    Given I navigate to the Shadow DOM page
    Then I should see the text "Let's have some different text!" in the shadow element