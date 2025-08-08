public class ScenarioGeneratorTest {
    public static void main(String[] args) {
        var result = ScenarioGenerator.generateScenario(
                "Cloud Computing",
                "Software Engineer",
                "Cloud Infrastructure"
        );

        if (result.get("inputs") == null || result.get("scenario") == null) {
            throw new AssertionError("Test failed: result structure is wrong");
        }
        System.out.println("✅ All tests passed!");
    }
}
