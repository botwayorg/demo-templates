import "package:nyxx/nyxx.dart";
import "package:botway_dart/botway_dart.dart";

// Main function
void main() {
  // Configure bot
  var bot_config = Botway();

  // Create new bot instance
  final bot =
      NyxxFactory.createNyxxWebsocket(bot_config.Get_Token(), GatewayIntents.allUnprivileged)
        ..registerPlugin(Logging()) // Default logging plugin
        ..registerPlugin(
            CliIntegration()) // Cli integration for nyxx allows stopping application via SIGTERM and SIGKILl
        ..registerPlugin(
            IgnoreExceptions()) // Plugin that handles uncaught exceptions that may occur
        ..connect();

  // Listen to ready event. Invoked when bot is connected to all shards. Note that cache can be empty or not incomplete.
  bot.eventsWs.onReady.listen((e) {
    print("Ready!");
  });

  // Listen to all incoming messages
  bot.eventsWs.onMessageReceived.listen((e) {
    // Check if message content equals "ping"
    if (e.message.content == "ping") {
      // Send "Pong!" to channel where message was received
      e.message.channel.sendMessage(MessageBuilder.content("Pong!"));
    }
  });
}
