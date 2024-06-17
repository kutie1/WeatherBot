package kutie.telebot.weatherbot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestRequest {
    public static void main(String[] args) throws Exception {
        // Координати Батурина: 51.346800, 32.872284
        // Координати Києва: 50.463117, 30.405354
        String uri = "https://api.weatherbit.io/v2.0/current?" +
                "lat=50.463117&lon=30.405354&key=f2e365f8dbbf4596af853bbad833c382&include=daily";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Files.write(Path.of("src/main/resources/file.json"), body.getBytes());
        }
    }
}
