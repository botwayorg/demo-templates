package core;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class TGBot extends BotHandler {
    private final TelegramBot bot = new TelegramBot(botway.BotwayKt.GetToken());

    @Override
    void onWebhookUpdate(Update update) {
        Long chatId = update.message().chat().id();
        bot.execute(new SendMessage(chatId, "Hello from bot"));
    }
}
