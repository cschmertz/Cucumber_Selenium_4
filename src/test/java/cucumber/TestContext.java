package cucumber;

import managers.PageObjectManager;
import managers.DriverManager;

import java.net.MalformedURLException;

public class TestContext {

    private DriverManager driverManager;
    private PageObjectManager pageObjectManager;
    ScenarioContext scenarioContext;

    public TestContext() {
        driverManager = new DriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
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
