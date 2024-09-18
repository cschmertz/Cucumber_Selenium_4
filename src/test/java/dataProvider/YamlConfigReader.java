package dataProvider;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.List;

public class YamlConfigReader {
    private Map<String, Object> config;

    public YamlConfigReader() {
        Yaml yaml = new Yaml();
        File file = new File("browserstack.yml");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            config = yaml.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load browserstack.yml", e);
        }
    }

    public YamlConfigReader(String configFile) {
        // Initialize the YamlConfigReader with the given config file
    }

    public String getUserName() {
        return (String) config.get("userName");
    }

    public String getAccessKey() {
        return (String) config.get("accessKey");
    }

    public String getProjectName() {
        return (String) config.get("projectName");
    }

    public String getBuildName() {
        return (String) config.get("buildName");
    }

    public String getBuildIdentifier() {
        return (String) config.get("buildIdentifier");
    }

    public int getParallelsPerPlatform() {
        return (int) config.get("parallelsPerPlatform");
    }

    public boolean getBrowserstackLocal() {
        return (boolean) config.get("browserstackLocal");
    }

    public boolean getDebug() {
        return (boolean) config.get("debug");
    }

    public boolean getNetworkLogs() {
        return (boolean) config.get("networkLogs");
    }

    public String getConsoleLogs() {
        return (String) config.get("consoleLogs");
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getAllPlatforms() {
        return (List<Map<String, Object>>) config.get("platforms");
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getFirstPlatform() {
        return ((List<Map<String, Object>>) config.get("platforms")).get(0);
    }
}
