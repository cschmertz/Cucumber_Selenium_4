package stepDefinitions;

import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.And;
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
import java.util.Map;

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
    public void iEnterBookstore() throws Exception {
        browserUtils.clickWithJS(bookStorePage.bookStoreButton);


    }

    @And("I search for a book")
    public void iSearchForABook() {
        bookStorePage.bookSearch();
    }

    @Then("I should see that book in the search results")
    public void iShouldSeeThatBookInTheSearchResults() throws Exception {
        //Asserting the actual vs expected data from an Excel file(expected) and the Web element(actual)
        //First method of extracting data from the Web element is a List

        //Web element containing list of elements
        WebElement GitPocketGuide = bookStorePage.GitPocketGuideList;
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


//        //Setting the value of Web element from the Book bean and pairing it with the ENUM
//        testContext.getScenarioContext().setContext(Context.ISBN,GitPocketGuideBook.getIsbn());
//        //Printing out the result of calling the getContext method which retrieves the Key(ENUM) and it's value
//        System.out.println(testContext.getScenarioContext().getContext(Context.ISBN));
//        //Assertion comparing the two results
//        Assert.assertEquals(GitPocketGuideBook.getIsbn(),testContext.getScenarioContext().getContext(Context.ISBN));


        //Here we are doing the same thing as above, comparing actual vs expected, but instead we use the
//        //Scenario Context class that provides us with a HashMap in which to set and retrieve our data
//
//        //Web element containing list of elements
//        WebElement GitPocketGuideWrapper = bookStorePage.GitPocketGuideList;
//        //Finding and storing those elements into a list of Web elements
//        List<WebElement> GitPocketGuideListOfBook = GitPocketGuideWrapper.findElements(By.id("userName-value"));
//        //Converting list of elements to list of String
//        List<String> convertedList = browserUtils.getElementsText(GitPocketGuideListOfBook);
//        //Setting the HashMap to take the elements from list of String
//        testContext.getScenarioContext().setContext(Context.BOOK,convertedList);
//        //Printing the results
//        System.out.println(testContext.getScenarioContext().getContext(Context.BOOK));
//
//        WebElement isbn = bookStorePage.gitPocketGuideISBN;
//
//        testContext.getScenarioContext().setContext(Context.ISBN,isbn);
//
//        String actualISBN = (String) testContext.getScenarioContext().getContext(Context.ISBN);
//
//        String expectedISBN = excelUtil.getRowDataWithCellDataOnNewLine(0);







    }
}
