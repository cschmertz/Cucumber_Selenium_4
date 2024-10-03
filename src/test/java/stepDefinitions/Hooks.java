package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import managers.DriverManager;

public class Hooks {

    TestContext testContext;

    public Hooks() {
        testContext = TestContext.getInstance();
    }

    @Before
    public void setUp() {
        testContext.getWebDriverManager().getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("-----> After annotation: Closing browser");
        System.out.println("scenario.getName() = " + scenario.getName());
        System.out.println("scenario.isFailed() = " + scenario.isFailed());
        testContext.getWebDriverManager().closeDriver();
    }
}