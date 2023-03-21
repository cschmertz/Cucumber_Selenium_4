package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageObjects.BookStorePage;
import pageObjects.GooglePage;
import pageObjects.LoginPage;
import stepDefinitions.BookStoreLogin;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;

    private LoginPage loginPage;

    private BookStorePage bookStorePage;

    public BookStorePage getBookStorePage() {
        return (bookStorePage == null) ? bookStorePage = new BookStorePage(driver) : bookStorePage;
    }

    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }
    public LoginPage getLoginPage(){
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(driver) : browserUtils;
    }
}
