package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    TestContext testContext;

    public Hooks() {
        testContext = TestContext.getInstance();
    }

    @Before()
    public void BeforeSteps() {
        // Setup operations before each test
    }

    @AfterStep()
    public void addScreenshot(Scenario scenario) {
        testContext.getWebDriverManager().getScreenShot(scenario);
    }

    @After()
    public void AfterAll(Scenario scenario) {
        System.out.println("-----> After annotation: Closing browser");
        System.out.println("scenario.getName() = " + scenario.getName());
        System.out.println("scenario.getSourceTagNames() = " + scenario.getSourceTagNames());
        System.out.println("scenario.isFailed() = " + scenario.isFailed());

        testContext.getWebDriverManager().closeDriver();
    }
}