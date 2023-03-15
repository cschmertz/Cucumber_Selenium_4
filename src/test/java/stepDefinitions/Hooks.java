package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.BrowserUtils;

import java.sql.Driver;


public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before()
    public void BeforeSteps() {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
    }

    @After()
    public void AfterSteps(Scenario scenario) {

        System.out.println("-----> After annotation: Closing browser");
        System.out.println("scenario.getName() = " + scenario.getName());
        System.out.println("scenario.getSourceTagNames() = " + scenario.getSourceTagNames());
        System.out.println("scenario.isFailed() = " + scenario.isFailed());

        if (scenario.isFailed()) {

           testContext.getWebDriverManager().getScreenShot(scenario);




        }


        testContext.getWebDriverManager().closeDriver();
    }
}
