package core;

import com.pengrad.telegrambot.request.SetWebhook;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Bot {
    public static void main(String[] args) {
        BotHandler[] bots = new BotHandler[]{
            new TGBot()
        };

        get("/", (req, res) -> "This is a Telegram Bot, created with Botway and java-telegram-bot-api");
    }
}
