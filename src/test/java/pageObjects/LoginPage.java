package pageObjects;

import cucumber.TestContext;
import managers.FileReaderManager;
import managers.PageObjectManager;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;

import java.util.Date;
import java.util.Set;

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

    @FindBy(css = "p[id='name']")
    public WebElement loginErrorMessage;

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

    public void testLoginNegative(){
        userName.sendKeys(FileReaderManager.getInstance().getConfigReader().getProperty("negUsername"));
        password.sendKeys(FileReaderManager.getInstance().getConfigReader().getProperty("negPassword"));
        loginButton.click();
    }

    public void assertNegativeLogin(){
        String expected = "Invalid username or password!";
        String actual = loginErrorMessage.getText();
        Assert.assertEquals(expected,actual);
    }

    public void assertLoginUserNameLabel(){
        String actualUsernameLabel = UserNameLabel.getText();
        String expectedUserNameLabel = "UserUser123";

        Assert.assertEquals(actualUsernameLabel,expectedUserNameLabel);
    }



    public void logOut(){
        logOutButton.click();
    }

    public void checkCookies() {
        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Get all the cookies from the current page
        Set<Cookie> cookies = driver.manage().getCookies();

        // Check if cookies are present
        if (cookies.isEmpty()) {
            System.out.println("No cookies found on the current page: " + currentUrl);
        } else {
            System.out.println("Cookies found on the current page: " + currentUrl);
            for (Cookie cookie : cookies) {
                System.out.println("Name: " + cookie.getName() + ", Value: " + cookie.getValue());
            }
        }
    }

    public void addCookie(String name, String value, String domain, String path, int expiry) {
        Cookie cookie = new Cookie(name, value, domain, path, new Date(System.currentTimeMillis() + (expiry * 1000)));
        driver.manage().addCookie(cookie);
    }

    public Cookie getCookieNamed(String name) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    public void deleteCookieNamed(String name) {
        driver.manage().deleteCookieNamed(name);
    }





}
