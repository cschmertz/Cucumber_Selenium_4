package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.BookStorePage;
import utilities.BrowserUtils;
import utilities.ExcelUtil;

import java.util.List;

public class ListOfBooks {

    TestContext testContext;

    BookStorePage bookStorePage;

    BrowserUtils browserUtils;

    ExcelUtil excelUtil = new ExcelUtil("src/test/resources/DataFiles/ListOfBooks.xlsx","Sheet1");

    public ListOfBooks(TestContext context){
        testContext = context;
        bookStorePage = testContext.getPageObjectManager().getBookStorePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }


    @Given("I am on profile page")
    public void iAmOnProfilePage() {
        bookStorePage.pageTitle();
    }

    @When("I enter the bookstore")
    public void iEnterTheBookstore() {
        browserUtils.clickWithJS(bookStorePage.bookStoreButton);
    }

    @Then("I should be able to see all the books available")
    public void iShouldBeAbleToSeeAllTheBooksAvailable() {
        WebElement table = bookStorePage.tableOfBooks;

        List<WebElement> tableOfBooks = table.findElements(By.tagName("a"));
        List<String> tableNames = browserUtils.getElementsText(tableOfBooks);

        for (String names:tableNames) {
            System.out.println(names);
        }

        WebElement tableBody = bookStorePage.tableOfBooks;

        List<WebElement> tableBodyOfElements = tableBody.findElements(By.tagName("img"));

        for (WebElement elem:tableBodyOfElements) {
            System.out.println(elem.getAttribute("src"));

        }
    }



}
