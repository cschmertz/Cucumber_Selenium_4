package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import dataProvider.YamlConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private YamlConfigReader yamlConfigReader;

    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
        yamlConfigReader = new YamlConfigReader();
    }

    public void getScreenShot(Scenario scenario) {
        WebDriver webDriver = getDriver();
        if (webDriver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private WebDriver createDriver() {
        logger.info("Creating driver for environment: {}", environmentType);
        switch (environmentType) {
            case LOCAL:
                return createLocalDriver();
            case REMOTE:
                return createRemoteDriver();
            case BROWSERSTACK:
                return createBrowserStackDriver();
            default:
                throw new RuntimeException("Unsupported environment type: " + environmentType);
        }
    }

    private WebDriver createLocalDriver() {
        logger.info("Creating local driver for browser: {}", driverType);
        switch (driverType) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new RuntimeException("Unsupported driver type: " + driverType);
        }
    }

    private WebDriver createRemoteDriver() {
        logger.info("Creating remote driver");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(driverType.toString().toLowerCase());
        try {
            return new RemoteWebDriver(new URL(FileReaderManager.getInstance().getConfigReader().getRemoteUrl()), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote WebDriver URL", e);
        }
    }

    private WebDriver createBrowserStackDriver() {
        List<Map<String, Object>> allPlatforms = yamlConfigReader.getAllPlatforms();
        
        for (Map<String, Object> platform : allPlatforms) {
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", platform.get("browserName"));
            capabilities.setCapability("browserVersion", platform.get("browserVersion"));
            capabilities.setCapability("os", platform.get("os"));
            capabilities.setCapability("osVersion", platform.get("osVersion"));

            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("userName", yamlConfigReader.getUserName());
            browserstackOptions.put("accessKey", yamlConfigReader.getAccessKey());
            browserstackOptions.put("projectName", yamlConfigReader.getProjectName());
            browserstackOptions.put("buildName", yamlConfigReader.getBuildName());
            browserstackOptions.put("sessionName", "BStack-[Java] Sample Test");
            capabilities.setCapability("bstack:options", browserstackOptions);
            
            try {
                return new RemoteWebDriver(
                    new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                logger.error("Failed to create BrowserStack driver for platform: {}", platform, e);
                // Continue to the next platform if this one fails
            }
        }
        
        throw new RuntimeException("Failed to create BrowserStack driver for any platform");
    }

    public void closeDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Closing driver");
            webDriver.quit();
            driver.remove();
        }
    }
}
