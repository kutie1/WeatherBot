package kutie.telebot.weatherbot;

public class View {
    View() {
        String token = System.getenv("KATE_WEATHERBITIO_BOT_TOKEN");
        System.out.println(token);
    }
}
