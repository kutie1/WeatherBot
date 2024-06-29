package kutie.telebot.weatherbot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestWeather10Days {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String url = "http://api.weatherbit.io/v2.0/forecast/hourly?";
        url += "key=74cfaf869ef54ef090de18c31cec9e6d";
        url += "&lang=uk";
        url += "&lat=50.463117&lon=30.405354";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Files.write(Path.of("src/main/resources/test-weather-10-days.json"), body.getBytes());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
