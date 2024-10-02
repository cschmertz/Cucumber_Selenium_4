package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.GooglePage;
import pageObjects.ShadowDomPage;
import pageObjects.ShadowDomTwoPage;
import utilities.BrowserUtils;

public class PageObjectManager {
    private WebDriver driver;

    private BrowserUtils browserUtils;
    private GooglePage googlePage;
    private ShadowDomPage shadowDomPage; // Declare the variable
    private ShadowDomTwoPage shadowDomTwoPage;

    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }

    public ShadowDomPage getShadowDomPage() {
        return (shadowDomPage == null) ? shadowDomPage = new ShadowDomPage(driver) : shadowDomPage;
    }

    public ShadowDomTwoPage getShadowDomTwoPage() {
        return (shadowDomTwoPage == null) ? shadowDomTwoPage = new ShadowDomTwoPage(driver) : shadowDomTwoPage;
    }

    public BrowserUtils getBrowserUtils(){
        return (browserUtils == null) ? browserUtils = new BrowserUtils(driver) : browserUtils;
    }
}
