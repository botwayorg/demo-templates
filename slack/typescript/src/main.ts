import { App, LogLevel } from "@slack/bolt";
import { GetToken, GetSecret, GetAppId } from "botway.js";

const app = new App({
  socketMode: true,
  token: GetToken(),
  signingSecret: GetSecret(),
  appToken: GetAppId(),
  logLevel: LogLevel.DEBUG,
});

app.command("/hello", async ({ body, ack, say }) => {
  await ack();

  await say(`Hi ${body.user_name}`);
});

app.event("app_mention", async ({ event, say }) => {
  await say("What's up?");
});

(async () => {
  await app.start(process.env.PORT || 8080);

  console.log("⚡️ Bolt app is running!");
})();
