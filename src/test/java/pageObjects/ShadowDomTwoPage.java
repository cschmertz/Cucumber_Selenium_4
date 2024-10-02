package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import managers.FileReaderManager;
import utilities.BrowserUtils;

public class ShadowDomTwoPage {
    WebDriver driver;
    WebElement shadowElement;
    BrowserUtils browserUtils;

    public ShadowDomTwoPage(WebDriver driver) {
        this.driver = driver;
        this.browserUtils = new BrowserUtils(driver);
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
}
