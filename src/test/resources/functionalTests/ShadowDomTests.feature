@ShadowDom @Suite
Feature: Shadow DOM Text Verification

@ShadowDom1
  Scenario: Verify text in shadow element
    Given I navigate to the Shadow DOM page
    Then I should see the text "Let's have some different text!" in the shadow element

@ShadowDom2
  Scenario: Verify button text in shadow element
    Given I navigate to the Expand Practice page
    When I initialize the shadow element
    Then I should see the text "This button is inside a Shadow DOM." in the button
