package runners;

import cucumber.TestContext;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import managers.DriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

            Properties props = new Properties();
            try {
                props.load(new FileInputStream("browserstack.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String key : props.stringPropertyNames()) {
                System.setProperty(key, props.getProperty(key));
            }
        }
}