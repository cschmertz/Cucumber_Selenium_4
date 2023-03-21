package stepDefinitions;

import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.BookStorePage;
import pageObjects.LoginPage;
import pojo.BookBean;
import utilities.BrowserUtils;

public class BookStoreSearch {

    TestContext testContext;

    BookStorePage bookStorePage;

    BrowserUtils browserUtils;

    public BookStoreSearch(TestContext context){
        testContext = context;
        bookStorePage = testContext.getPageObjectManager().getBookStorePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }
    @When("I enter bookstore")
    public void iEnterBookstore() {
        browserUtils.clickWithJS(bookStorePage.bookStoreButton);

    }

    @And("I search for a book")
    public void iSearchForABook() {
        bookStorePage.bookSearch();
    }

    @Then("I should see that book in the search results")
    public void iShouldSeeThatBookInTheSearchResults() {
        //Creating Book bean
        BookBean GitPocketGuide = new BookBean();
        //Setting the text of the Web element to the ISBN field of our Book bean
        GitPocketGuide.setIsbn(bookStorePage.gitPocketGuideISBN.getText());
        //Printing out the Book bean
        System.out.println(GitPocketGuide.getIsbn());
        //Setting the value of Web element from the Book bean and pairing it with the ENUM
        testContext.getScenarioContext().setContext(Context.ISBN,GitPocketGuide.getIsbn());
        //Printing out the result of calling the getContext method which retrieves the Key(ENUM) and it's value
        System.out.println(testContext.getScenarioContext().getContext(Context.ISBN));
        //Assertion comparing the two results
        Assert.assertEquals(GitPocketGuide.getIsbn(),testContext.getScenarioContext().getContext(Context.ISBN));


    }
}
