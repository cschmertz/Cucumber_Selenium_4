package pageObjects;

import cucumber.TestContext;
import managers.FileReaderManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;



    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "input[placeholder='UserName']")
    public WebElement userName;
    @FindBy(css = "input[placeholder='Password']")
    public WebElement password;
    @FindBy(css = "button[id='login']")
    public WebElement loginButton;
    @FindBy(css = "button[id='submit']")
    public WebElement logOutButton;
    @FindBy(css = "label[id='userName-value']")
    public WebElement UserNameLabel;

    public void NavigateToBookStoreHomePage(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
    }

    public void pageTitle(){
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
    }

    public void loginPositive(){
        userName.sendKeys(FileReaderManager.getInstance().getConfigReader().getProperty("username"));
        password.sendKeys(FileReaderManager.getInstance().getConfigReader().getProperty("password"));
    }

    public void assertLoginUserNamelabel(){
        String actualUsernameLabel = UserNameLabel.getText();
        String expectedUserNameLabel = "UserUser123";

        Assert.assertEquals(actualUsernameLabel,expectedUserNameLabel);
    }

    public void logOut(){
        logOutButton.click();
    }


}
