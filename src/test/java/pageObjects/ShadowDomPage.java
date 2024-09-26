package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.FileReaderManager;

import java.util.List;

public class ShadowDomPage {
   
    WebDriver driver;

    public ShadowDomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToShadowDomPage() {
        driver.get(FileReaderManager.getInstance().getConfigReader().getShadowDomUrl());
    }

    public String getShadowElementText() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement shadowHost = driver.findElement(By.cssSelector("my-paragraph:nth-of-type(2)"));
        
        // Check if shadowHost is found
        if (shadowHost == null) {
            throw new RuntimeException("Shadow host not found");
        }

        // Wait for the shadow root to be available
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(shadowHost));

        // Wait for the list item to be present in the shadow DOM
        WebElement listItem = (WebElement) js.executeScript(
            "return arguments[0].shadowRoot ? arguments[0].shadowRoot.querySelector('ul li') : null;", shadowHost);

        // Check if listItem is found
        if (listItem == null) {
            // Wait for the list item to be present
            wait.until(driver -> (WebElement) js.executeScript(
                "return arguments[0].shadowRoot ? arguments[0].shadowRoot.querySelector('ul li') : null;", shadowHost));
            
            listItem = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot ? arguments[0].shadowRoot.querySelector('ul li') : null;", shadowHost);
            
            if (listItem == null) {
                throw new RuntimeException("List item not found in shadow DOM");
            }
        }

        return listItem.getText();
    }
}