package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;


    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public void getScreenShot(Scenario scenario){

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
            case BROWSERSTACK:
                try{
                    driver = createBrowserStackDriver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return driver;
    }

    public WebDriver createBrowserStackDriver() throws Exception {

        String USERNAME = System.getProperty("user");
        String AUTOMATE_KEY = System.getProperty("key");
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
        String buildName = "";
        String projectName = "";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser",System.getProperty("browserstack.browser"));
        caps.setCapability("browser_version", System.getProperty("browserstack.browser_version"));
        caps.setCapability("os", System.getProperty("browserstack.os"));
        caps.setCapability("os_version", System.getProperty("browserstack.os_version"));
        caps.setCapability("resolution", System.getProperty("browserstack.resolution"));
        caps.setCapability("project", projectName);
        caps.setCapability("build", buildName);
        caps.setCapability("name", "Your Test Name");

        return new RemoteWebDriver(new URL(URL), caps);
    }


    private WebDriver createRemoteDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("platform", Platform.ANY);

        switch (driverType) {
            case REMOTECHROME:
                desiredCapabilities.setBrowserName(BrowserType.CHROME);
//                desiredCapabilities.setCapability("chromeOptions", createChromeOptions(true));
                break;
            case REMOTEFIREFOX:
                desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                desiredCapabilities.setCapability("marionette", true);
                break;
            default:
                throw new RuntimeException("Invalid driver type");
        }

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for remote webdriver", e);
        }
    }

//    private ChromeOptions createChromeOptions(boolean headless) {
//        ChromeOptions options = new ChromeOptions();
//        if (headless) {
//            options.addArguments("--headless"); // Set Chrome to run in headless mode
//        }
//        // You can add more Chrome options here if needed
//        return options;
//    }

    private WebDriver createLocalDriver() {
        switch (driverType) {

            case FIREFOX :
                WebDriverManager.firefoxdriver().setup();
                driver= new FirefoxDriver();
                break;
            case CHROME :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
    public void closeDriver() {
        driver.quit();
    }

}
