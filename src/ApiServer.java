import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;

public class ApiServer {
    public static void startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/generate", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if ("POST".equals(exchange.getRequestMethod())) {

                    // Read request body
                    BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        body.append(line);
                    }
                    String requestBody = body.toString();

                    // Extract simple values from JSON manually (quick & dirty parsing)
                    String tech = extractValue(requestBody, "technology");
                    String role = extractValue(requestBody, "role");
                    String env = extractValue(requestBody, "environment");

                    Map<String, Object> scenario = ScenarioGenerator.generateScenario(tech, role, env);

                    // Build JSON manually
                    String json = "{ \"inputs\": { " +
                            "\"technology\": \"" + ((Map) scenario.get("inputs")).get("technology") + "\", " +
                            "\"role\": \"" + ((Map) scenario.get("inputs")).get("role") + "\", " +
                            "\"environment\": \"" + ((Map) scenario.get("inputs")).get("environment") + "\" }, " +
                            "\"scenario\": { " +
                            "\"challenge\": \"" + ((Map) scenario.get("scenario")).get("challenge") + "\", " +
                            "\"incident\": \"" + ((Map) scenario.get("scenario")).get("incident") + "\", " +
                            "\"troubleshootingStep\": \"" + ((Map) scenario.get("scenario")).get("troubleshootingStep") + "\" } }";

                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, json.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(json.getBytes());
                    os.close();
                } else {
                    exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                }
            }
        });

        server.start();
        System.out.println("Server started on port " + port);
    }

    // Simple helper to extract values from JSON-like string
    private static String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int start = json.indexOf(searchKey);
        if (start == -1) return "";
        start += searchKey.length();
        int firstQuote = json.indexOf("\"", start);
        int secondQuote = json.indexOf("\"", firstQuote + 1);
        return json.substring(firstQuote + 1, secondQuote);
    }
}
