package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelUtil;

public class BookStorePage {

    WebDriver driver;

    ExcelUtil excelUtil = new ExcelUtil();

    public BookStorePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "button[id='gotoStore']")
    public WebElement bookStoreButton;

    @FindBy(css = "div[class='profile-wrapper']")
    public WebElement GitPocketGuideList;

    @FindBy(css = "div[class='rt-table']")
    public WebElement tableOfBooks;

    @FindBy(css = "input[placeholder='Type to search']")
    public WebElement bookStorePageSearchBox;

    @FindBy(css = "span[id='see-book-Git Pocket Guide']")
    public WebElement gitPocketGuideBook;

    @FindBy(css = "div[id='ISBN-wrapper']")
    public WebElement gitPocketGuideISBN;

    @FindBy(css = "div[id='title-wrapper']")
    public WebElement gitPocketGuideTitle;

    @FindBy(css = "div[id='subtitle-wrapper']")
    public WebElement gitPocketGuideSubTitle;

    @FindBy(css = "div[id='author-wrapper']")
    public WebElement gitPocketGuideAuthor;

    @FindBy(css = "div[id='publisher-wrapper']")
    public WebElement gitPocketGuidePublisher;

    @FindBy(css = "div[id='pages-wrapper']")
    public WebElement gitPocketGuidePages;

    @FindBy(css = "div[id='description-wrapper']")
    public WebElement gitPocketGuideDescription;

    @FindBy(css = "div[id='website-wrapper']")
    public WebElement gitPocketGuideWebsite;


    public void bookSearch(){
        bookStorePageSearchBox.sendKeys("Git Pocket Guide", Keys.ENTER);
        gitPocketGuideBook.click();
    }



    Object [] [] GitPocketGuideData = new Object[8][2];
    String filePath = "src/test/resources/DataFiles/GitPocketGuideBook.xlsx";

    public void hardCodedBookDataIntoExcelFile() throws Exception {

        GitPocketGuideData[0][0] = "ISBN :";
        GitPocketGuideData[0][1] = "9781449325862";
        GitPocketGuideData[1][0] = "Title :";
        GitPocketGuideData[1][1] = "Git Pocket Guide";
        GitPocketGuideData[2][0] = "Sub Title :";
        GitPocketGuideData[2][1] = "A Working Introduction";
        GitPocketGuideData[3][0] = "Author :";
        GitPocketGuideData[3][1] = "Richard E. Silverman";
        GitPocketGuideData[4][0] = "Publisher :";
        GitPocketGuideData[4][1] = "O'Reilly Media";
        GitPocketGuideData[5][0] = "Total Pages :";
        GitPocketGuideData[5][1] = 234;
        GitPocketGuideData[6][0] = "Description :";
        GitPocketGuideData[6][1] = "This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp";
        GitPocketGuideData[7][0] = "Website :";
        GitPocketGuideData[7][1] = "http://chimera.labs.oreilly.com/books/1230000000561/index.html";

        excelUtil.writeTableToExcel(GitPocketGuideData,filePath);
    }




}
