import java.util.Arrays;
import java.util.List;

public class Dataset {
    public static final List<String> CHALLENGES = Arrays.asList(
            "Latency issues under high load",
            "Security vulnerabilities in outdated dependencies",
            "Inconsistent configuration across environments"
    );

    public static final List<String> INCIDENTS = Arrays.asList(
            "Database outage during peak hours",
            "Unauthorized access detected",
            "Service downtime due to misconfigured DNS"
    );

    public static final List<String> TROUBLESHOOTING_STEPS = Arrays.asList(
            "Restart the affected services",
            "Apply latest security patches",
            "Scale up resources to handle load"
    );
}
