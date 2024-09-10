package cucumber;

import managers.PageObjectManager;
import managers.DriverManager;

public class TestContext {

    private static volatile TestContext instance;
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

        pageObjectManager = null;

        if (scenarioContext != null) {
            scenarioContext.clearContext();
        }
    }
}
