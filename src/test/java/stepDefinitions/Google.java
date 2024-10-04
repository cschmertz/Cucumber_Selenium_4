package stepDefinitions;

import cucumber.TestContext;
import dataProvider.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GooglePage;
import utilities.BrowserUtils;
import java.io.IOException;


public class Google {

    TestContext testContext;
    GooglePage googlepage;
    BrowserUtils browserUtils;


    public Google(){
        testContext = TestContext.getInstance();
        googlepage = testContext.getPageObjectManager().getGooglePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @Given("I navigate to Google search page")
    public void i_navigate_to_google_search_page() {
        googlepage.NavigateToGoogleHomePage();

    }
    @When("I check page title")
    public void i_check_page_title() {
        googlepage.pageTitle();
    }
    @Then("I have page title assertion")
    public void i_have_page_title_assertion() throws IOException {
        googlepage.pageTitleValidation();


    }

}
