package pageObjects;

import managers.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GooglePage  {
    WebDriver driver;


    public GooglePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void NavigateToGoogleHomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }

    public void pageTitle(){
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    }

    public void pageTitleValidation() {
        String expectedPageTitle = "Google";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle,actualPageTitle);


    }
}
