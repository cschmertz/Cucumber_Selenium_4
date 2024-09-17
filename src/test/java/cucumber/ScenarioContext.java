package cucumber;

import enums.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {

    private final Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new ConcurrentHashMap<>();
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }


    public Object getContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    public <T> T getContext(Context key, Class<T> type) {

        Object value = scenarioContext.get(key.toString());

        if (value != null && type.isInstance(value)) {
            return type.cast(value);
        }

        return null;
    }

    public Boolean containsKey(Context key) {
        return scenarioContext.containsKey(key.toString());
    }

    public void clearContext() {
        scenarioContext.clear();
    }

}
