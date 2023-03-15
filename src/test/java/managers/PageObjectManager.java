package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.GooglePage;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;
    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(driver) : browserUtils;
    }
}
