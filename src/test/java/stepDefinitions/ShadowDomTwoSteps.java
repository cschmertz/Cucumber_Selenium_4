package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ShadowDomTwoPage;
import utilities.BrowserUtils;

import static org.junit.Assert.assertEquals;

public class ShadowDomTwoSteps {
    TestContext testContext;
    ShadowDomTwoPage shadowDomTwoPage;
    BrowserUtils browserUtils;

    public ShadowDomTwoSteps() {
        testContext = TestContext.getInstance();
        shadowDomTwoPage = new ShadowDomTwoPage(testContext.getWebDriver());
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @Given("I navigate to the Expand Practice page")
    public void i_navigate_to_the_expand_practice_page() {
        shadowDomTwoPage.navigateToShadowDomPage();
    }

    @When("I initialize the shadow element")
    public void i_initialize_the_shadow_element() {
        shadowDomTwoPage.initializeShadowElement();
    }

    @Then("I should see the text {string} in the button")
    public void i_should_see_the_text_in_the_button(String expectedText) {
        String actualText = shadowDomTwoPage.getButtonText();
        assertEquals(expectedText, actualText);
        System.out.println("Button text inside the shadow DOM element: " + actualText);
    }
}