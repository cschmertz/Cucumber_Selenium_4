package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.GooglePage;

public class PageObjectManager {
    private WebDriver driver;

    private GooglePage googlePage;
    public PageObjectManager(WebDriver driver) {
                this.driver = driver;
    }

    public GooglePage getGooglePage(){
        return (googlePage == null) ? googlePage = new GooglePage(driver) : googlePage;
    }
}
