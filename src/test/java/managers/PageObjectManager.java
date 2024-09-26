package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.GooglePage;
import pageObjects.ShadowDomPage;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;
    private ShadowDomPage shadowDomPage; // Declare the variable

    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }

    public ShadowDomPage getShadowDomPage() {
        return (shadowDomPage == null) ? shadowDomPage = new ShadowDomPage(driver) : shadowDomPage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(driver) : browserUtils;
    }
}
