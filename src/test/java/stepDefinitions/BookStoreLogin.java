package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import utilities.BrowserUtils;

import java.io.IOException;

public class BookStoreLogin {

    TestContext testContext;

    LoginPage loginPage;

    BrowserUtils browserUtils;

    public BookStoreLogin(TestContext context){
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }
    @Given("I am on bookstore login page")
    public void iAmOnBookstoreLoginPage() {
        loginPage.NavigateToBookStoreHomePage();
    }

    @When("I enter credentials")
    public void iEnterCredentials() throws IOException {
        loginPage.loginPositive();
        browserUtils.clickWithJS(loginPage.loginButton);
        browserUtils.getScreenshot("login page");
        loginPage.pageTitle();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() throws IOException {
        browserUtils.waitForVisibility(loginPage.UserNameLabel,10);
        loginPage.assertLoginUserNamelabel();
        browserUtils.getScreenshot("bookstore dashboard");
        browserUtils.scrollToElement(loginPage.logOutButton);
        loginPage.logOut();
    }
}
