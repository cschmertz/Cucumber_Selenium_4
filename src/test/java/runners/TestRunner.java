package runners;

import cucumber.TestContext;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import managers.DriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/functionalTests"},
        glue= {"stepDefinitions"},
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "listeners.TestFailureListener"},
        monochrome = true,
        tags = "@Google",
        dryRun = false
)
public class TestRunner {

    private static TestContext testContext;
    private static DriverManager driverManager;

    @BeforeClass
    public static void setup() {
        testContext = TestContext.getInstance();
        driverManager = testContext.getWebDriverManager();

        String executionMode = System.getProperty("executionMode", "local");
        if ("browserstack".equalsIgnoreCase(executionMode)) {
            try {
                WebDriver driver = driverManager.createBrowserStackDriver();
                // Set the driver for your tests
                testContext.setWebDriver(driver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Existing local driver setup
            WebDriver driver = driverManager.getDriver();
            testContext.setWebDriver(driver);
        }
    }
}