package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cucumber.TestContext;
import managers.FileReaderManager;
import utilities.BrowserUtils;

public class ShadowDomTwoPage {
    WebDriver driver;
    WebElement shadowElement;
    BrowserUtils browserUtils;
    TestContext testContext;

    public ShadowDomTwoPage(WebDriver driver) {
        testContext = TestContext.getInstance();
        this.driver = driver;
        this.browserUtils = testContext.getPageObjectManager().getBrowserUtils();
        PageFactory.initElements(driver, this);
    }

    public void navigateToShadowDomPage() {
        driver.get(FileReaderManager.getInstance().getConfigReader().getShadowDomTwoUrl());
    }

    public void initializeShadowElement() {
        shadowElement = browserUtils.getShadowElement(driver, "#shadow-host", "button");
    }

    public String getButtonText() {
        return shadowElement.getText();
    }

    public WebElement getButtonLocator() {
        return shadowElement; // Assuming shadowElement is the button you want to locate
    }
}
