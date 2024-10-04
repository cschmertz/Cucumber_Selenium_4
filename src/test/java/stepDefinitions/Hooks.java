package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.time.LocalDateTime;

public class Hooks {

    TestContext testContext;

    public Hooks() {
        testContext = TestContext.getInstance();
    }

    @Before
    public void setUp() {
        testContext.getWebDriverManager().getDriver(); // Ensure the driver is initialized
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        testContext.getWebDriverManager().getScreenShot(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("-----> After annotation: Scenario completed with failure");
            System.out.println("Scenario ID: " + scenario.getId());
            System.out.println("Scenario Name: " + scenario.getName());
            System.out.println("Scenario Status: Failed");
            System.out.println("Error Message: " + scenario.getStatus());
            System.out.println("Timestamp: " + LocalDateTime.now());
        }
    }

    @AfterAll
    public static void tearDownAll() {
        TestContext.getInstance().getWebDriverManager().closeDriver();
    }

}