package stepDefinitions;

import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.BookStorePage;
import pojo.Book;
import utilities.BrowserUtils;
import utilities.ExcelUtil;

import java.util.List;

public class BookStoreSearch {

    TestContext testContext;

    BookStorePage bookStorePage;

    BrowserUtils browserUtils;

    ExcelUtil excelUtil = new ExcelUtil("src/test/resources/DataFiles/GitPocketGuideBook.xlsx","Sheet1");

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
        //Asserting the actual vs expected data from an Excel file(expected) and the Web element(actual)
        //First method of extracting data from the Web element is a List

        //Web element containing list of elements
        WebElement GitPocketGuide = bookStorePage.GitPocketGuideWrapper;
        //Finding and storing those elements into a list of Web elements
        List<WebElement> GitPocketGuideList = GitPocketGuide.findElements(By.id("userName-value"));
        //Converting the list of Web elements into a list of String
        List<String> actual = browserUtils.getElementsText(GitPocketGuideList);
        //Printing the results
        System.out.println(actual);

        //actual isbn is index 0 of our List of Web elements
        String actualIsbn = actual.get(0);
        //expected isbn is row 0, column 1 of our Excel Spread sheet containing our hard coded data
        String expectedIsbn = excelUtil.getCellData(0, 1);
        //Asserting the two values are the same
        Assert.assertEquals(actualIsbn,expectedIsbn);

        //Another way to extract the data from the WEb element is to store it in our Bean as an object
        //then we compare it to the expected data from the Excel file

        //Creating Book bean
        Book GitPocketGuideBook = new Book();
        //Setting the text of the Web element to the ISBN field of our Book bean
        GitPocketGuideBook.setIsbn(bookStorePage.gitPocketGuideISBN.getText());
        //Actual String data from Web element
        String actualIsbnData = GitPocketGuideBook.getIsbn();
        //Expected data from our Excel file
        String expectedIsbnData = excelUtil.getRowDataWithCellDataOnNewLine(0);
        //Asserting the two values are the same
        Assert.assertEquals(actualIsbnData,expectedIsbnData);


        //Setting the value of Web element from the Book bean and pairing it with the ENUM
        testContext.getScenarioContext().setContext(Context.ISBN,GitPocketGuideBook.getIsbn());
        //Printing out the result of calling the getContext method which retrieves the Key(ENUM) and it's value
        System.out.println(testContext.getScenarioContext().getContext(Context.ISBN));
        //Assertion comparing the two results
        Assert.assertEquals(GitPocketGuideBook.getIsbn(),testContext.getScenarioContext().getContext(Context.ISBN));


        //Here we are doing the same thing as above, comparing actual vs expected, but instead we use the
        //Scenario Context class that provides us with a HashMap in which to set and retrieve our data

        //Web element containing list of elements
        WebElement GitPocketGuideWrapper = bookStorePage.GitPocketGuideWrapper;
        //Finding and storing those elements into a list of Web elements
        List<WebElement> GitPocketGuideListOfBook = GitPocketGuideWrapper.findElements(By.id("userName-value"));
        //Converting list of elements to list of String
        List<String> convertedList = browserUtils.getElementsText(GitPocketGuideListOfBook);
        //Setting the HashMap to take the elements from list of String
        testContext.getScenarioContext().setContext(Context.BOOK,convertedList);
        //Printing the results
        System.out.println(testContext.getScenarioContext().getContext(Context.BOOK));

        //Setting the String result of the Web element to the Context-Key ISBN
        testContext.getScenarioContext().setContext(Context.ISBN,bookStorePage.gitPocketGuideISBN.getText());
        //get Context key is Object, but returns the Context-Key to String
        Object actualISBN = testContext.getScenarioContext().getContext(Context.ISBN);
        //Data from Excel file
        String expectedISBN = excelUtil.getRowDataWithCellDataOnNewLine(0);
        //Comparing two values
        Assert.assertEquals(actualISBN,expectedISBN);
    }

    @Given("I am in the bookstore")
    public void i_am_in_the_bookstore() {
        bookStorePage.bookStoreButton.click();
    }

    @When("I select a book")
    public void i_select_a_book() {
        bookStorePage.gitPocketGuideBook.click();
    }

    @Then("I am provided details regarding that book")
    public void i_am_provided_details_regarding_that_book() {
        bookStorePage.assertBookSelection();
    }

    @When("I enter keys into the search bar")
    public void i_enter_keys_into_the_search_bar() {
        bookStorePage.bookStorePageSearchBox.sendKeys("gi");
    }
    @Then("the book selection should dynamically filter")
    public void the_book_selection_should_dynamically_filter() {
        bookStorePage.assertBookFilter();
    }

}
