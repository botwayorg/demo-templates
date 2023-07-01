import { Bot } from "grammy";
import { getToken } from "denobot";
import { logger } from "./logger.ts";

const log = logger({ name: "Event: Ready" });

// Create bot object
const bot = new Bot(getToken());

log.info("Starting Bot, this might take a while...");

// Listen for messages
bot.command("start", (ctx) => ctx.reply("Welcome! Send me a photo!"));
bot.on("message:text", (ctx) => ctx.reply("That is text and not a photo!"));
bot.on("message:photo", (ctx) => ctx.reply("Nice photo! Is that you?"));
bot.on("edited_message", (ctx) =>
  ctx.reply("Ha! Gotcha! You just edited this!", {
    reply_to_message_id: ctx.editedMessage.message_id,
  })
);

// Launch!
bot.start();

log.info("Bot Ready");
