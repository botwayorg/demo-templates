import { TwitchChat } from "tmi";
import { getToken } from "denobot";
import { logger } from "./logger.ts";

const log = logger({ name: "Event: Ready" });
const tc = new TwitchChat(getToken(), "USERNAME");

try {
  await tc.connect();

  const channel = tc.joinChannel("CHANNEL_NAME"); // you can use your username as channel name

  channel.addEventListener("privmsg", (ircMsg) => {
    if (ircMsg.message.includes("Hello")) {
      channel.send(`Yo ${ircMsg.username}`);
    }
  });

  log.info("Bot Ready");
} catch (err) {
  log.error(err);
}
