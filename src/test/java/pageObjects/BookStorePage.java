package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage {

    WebDriver driver;



    public BookStorePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "button[id='gotoStore']")
    public WebElement bookStoreButton;

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




}
