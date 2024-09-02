package listeners;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import java.util.Properties;
import java.util.logging.*;

public class TestFailureListener implements EventListener {

    private static final Logger logger = Logger.getLogger(TestFailureListener.class.getName());

    private String collectSystemInfo() {
        Properties props = System.getProperties();
        StringBuilder sb = new StringBuilder();
        sb.append("OS: ").append(props.getProperty("os.name")).append(" ")
                         .append(props.getProperty("os.version")).append("\n");
        sb.append("Java Version: ").append(props.getProperty("java.version")).append("\n");
        sb.append("User: ").append(props.getProperty("user.name")).append("\n");
        return sb.toString();
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        if (event.getResult().getStatus().is(Status.FAILED)) {
            String systemInfo = collectSystemInfo();
            System.out.println("System Information:\n" + systemInfo);
            logger.severe("Test failed: " + event.getTestCase().getName());
            logger.severe("Error: " + event.getResult().getError().getMessage());
            String scenarioName = event.getTestCase().getName();
            Throwable error = event.getResult().getError();

            System.out.println("========== TEST FAILURE ==========");
            System.out.println("Scenario: " + scenarioName);
            System.out.println("Error: " + error.getMessage());
            System.out.println("Stack trace:");
            error.printStackTrace(System.out);
            System.out.println("==================================");
        }
    }
}