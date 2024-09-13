package cucumber;

import managers.PageObjectManager;
import managers.DriverManager;
import org.openqa.selenium.WebDriver;

public class TestContext {

    private static volatile TestContext instance;
    private DriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;
    private WebDriver webDriver;

    private TestContext() {
        driverManager = new DriverManager();
        webDriver = driverManager.getDriver();
        pageObjectManager = new PageObjectManager(webDriver);
        scenarioContext = new ScenarioContext();
    }

    public static TestContext getInstance() {
        if (instance == null) {
            synchronized (TestContext.class) {
                if (instance == null) {
                    instance = new TestContext();
                }
            }
        }
        return instance;
    }

    public DriverManager getWebDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver driver) {
        this.webDriver = driver;
        this.pageObjectManager = new PageObjectManager(driver);
    }

    public static void reset() {
        synchronized (TestContext.class) {
            if (instance != null) {
                instance.cleanUp();
                instance = null;
            }
        }
    }

    public void cleanUp() {
        if (driverManager != null) {
            driverManager.closeDriver();
        }

        webDriver = null;
        pageObjectManager = null;

        if (scenarioContext != null) {
            scenarioContext.clearContext();
        }
    }

    public void initializeBrowserStackDriver() {
        try {
            WebDriver browserStackDriver = driverManager.createBrowserStackDriver();
            setWebDriver(browserStackDriver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize BrowserStack driver", e);
        }
    }
}