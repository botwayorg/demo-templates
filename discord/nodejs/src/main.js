const { REST } = require("@discordjs/rest");
const { WebSocketManager } = require("@discordjs/ws");
const {
  GatewayDispatchEvents,
  GatewayIntentBits,
  Client,
} = require("@discordjs/core");

const rest = new REST({ version: "10" }).setToken(process.env.DISCORD_TOKEN);

const gateway = new WebSocketManager({
  token: process.env.DISCORD_TOKEN,
  intents: GatewayIntentBits.GuildMessages | GatewayIntentBits.MessageContent,
  rest,
});

const client = new Client({ rest, gateway });

client.on(
  GatewayDispatchEvents.MessageCreate,
  async ({ data: msgData, api }) => {
    if (msgData.author.bot) {
      return;
    }

    if (msgData.content === "ping") {
      await api.channels.createMessage(msgData.channel_id, { content: "pong !" });
    }
  }
);

client.once(GatewayDispatchEvents.Ready, () => console.log("Ready!"));

gateway.connect();
