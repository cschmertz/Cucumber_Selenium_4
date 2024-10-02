package runners;

import org.junit.BeforeClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

import dataProvider.ConfigFileReader;
import dataProvider.YamlConfigReader;
import enums.EnvironmentType;


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
        tags = "@ShadowDom2",
        dryRun = false
)

public class TestRunner {

    private static ConfigFileReader configFileReader;
    private static YamlConfigReader yamlConfigReader;

    @BeforeClass
    public static void setup() {
        configFileReader = new ConfigFileReader();
        
        if (configFileReader.getEnvironment() == EnvironmentType.BROWSERSTACK) {
            yamlConfigReader = new YamlConfigReader();
        }
    }
}