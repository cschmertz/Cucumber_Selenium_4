package cucumber;

import managers.PageObjectManager;
import managers.DriverManager;

import java.net.MalformedURLException;

public class TestContext {

    private static TestContext instance;
    private DriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;

    private TestContext() {
        driverManager = new DriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
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
}
