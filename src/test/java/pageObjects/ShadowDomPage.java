package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.FileReaderManager;
import utilities.BrowserUtils;

import java.util.List;

public class ShadowDomPage {
   
    WebDriver driver;
    WebElement shadowElement; // Declare shadowElement here
    private BrowserUtils browserUtils;

    public ShadowDomPage(WebDriver driver) {
        this.driver = driver;
        this.browserUtils = new BrowserUtils(driver); // Initialize BrowserUtils
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