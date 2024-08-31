import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import utils.AdminPanel;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        AdminPanel adminPanel = new AdminPanel();
        Bot bot = new Bot(adminPanel);
        BotSession session = api.registerBot(bot);
        adminPanel.setSession(session);
        session.stop();
    }
}