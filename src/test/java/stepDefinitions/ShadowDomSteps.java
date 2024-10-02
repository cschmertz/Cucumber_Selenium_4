package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.ShadowDomPage;
import static org.junit.Assert.assertEquals; // Add this import

public class ShadowDomSteps {
    TestContext testContext;
    ShadowDomPage shadowDomPage;

    public ShadowDomSteps() {
        testContext = TestContext.getInstance();
        shadowDomPage = testContext.getPageObjectManager().getShadowDomPage();
    }

    @Given("I navigate to the Shadow DOM page")
    public void i_navigate_to_the_shadow_dom_page() {
        shadowDomPage.navigateToShadowDomPage();
    }

    @Then("I should see the text {string} in the shadow element")
    public void i_should_see_the_text_in_the_shadow_element(String expectedText) {
        shadowDomPage.initializeShadowElement();
        String actualText = shadowDomPage.getShadowElementText();
        assertEquals(expectedText, actualText);
        System.out.println("Text inside the shadow DOM element: " + actualText);
    }
}