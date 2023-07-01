import "package:teledart/teledart.dart";
import "package:teledart/telegram.dart";
import "package:botway_dart/botway_dart.dart";

void main() async {
  var bot_config = Botway().Get_Token();

  final username = (await Telegram(bot_config).getMe()).username;

  var teledart = TeleDart(bot_config, Event(username!));

  teledart
      .onCommand("start")
      .listen((message) => message.reply("Hello Telegram"));

  teledart.start();

  print("Bot started");
}
