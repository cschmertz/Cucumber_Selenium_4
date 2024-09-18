package runners;

import org.junit.BeforeClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


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

    @BeforeClass
    public static void setup() {
        String environment = System.getProperty("environment", "local");
        
        if ("browserstack".equalsIgnoreCase(environment)) {
            loadBrowserstackConfig();
        } else {
            loadDefaultConfig();
        }
    }

    private static void loadDefaultConfig() {
        loadPropertiesFile("configs/Configuration.properties");
    }

    private static void loadBrowserstackConfig() {
        loadPropertiesFile("browserstack.yml");
    }

    private static void loadPropertiesFile(String filePath) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(filePath));
            for (String key : props.stringPropertyNames()) {
                System.setProperty(key, props.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}