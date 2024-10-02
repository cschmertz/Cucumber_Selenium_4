package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.TestContext;
import managers.FileReaderManager;
import utilities.BrowserUtils;

import java.util.List;

public class ShadowDomPage {
   
    WebDriver driver;
    WebElement shadowElement;
    BrowserUtils browserUtils;
    TestContext testContext;

    public ShadowDomPage(WebDriver driver) {
        testContext = TestContext.getInstance();
        this.driver = driver;
        this.browserUtils = testContext.getPageObjectManager().getBrowserUtils();
        PageFactory.initElements(driver, this);
    }

    public void navigateToShadowDomPage() {
        driver.get(FileReaderManager.getInstance().getConfigReader().getShadowDomUrl());
    }

    public void initializeShadowElement() {
        shadowElement = browserUtils.getShadowElement(driver, "#content > my-paragraph:nth-child(4)", "p");
    }

    public String getShadowElementText() {
        return shadowElement.getText();
    }
}