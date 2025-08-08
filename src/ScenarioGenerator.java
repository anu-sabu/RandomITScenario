import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ScenarioGenerator {
    private static final Random RANDOM = new Random();

    private static String getRandomItem(List<String> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static Map<String, Object> generateScenario(String technology, String role, String environment) {
        Map<String, String> inputs = new HashMap<>();
        inputs.put("technology", technology);
        inputs.put("role", role);
        inputs.put("environment", environment);

        Map<String, String> scenario = new HashMap<>();
        scenario.put("challenge", getRandomItem(Dataset.CHALLENGES));
        scenario.put("incident", getRandomItem(Dataset.INCIDENTS));
        scenario.put("troubleshootingStep", getRandomItem(Dataset.TROUBLESHOOTING_STEPS));

        Map<String, Object> response = new HashMap<>();
        response.put("inputs", inputs);
        response.put("scenario", scenario);
        return response;
    }
}
