import telebot, asyncdispatch, logging, options, strutils
import botnim

var L = newConsoleLogger(fmtStr="$levelname, [$time] ")

addHandler(L)

proc updateHandler(b: Telebot, u: Update): Future[bool] {.async.} =
  if not u.message.isSome:
    return true

  var response = u.message.get

  if response.text.isSome:
    let text = response.text.get

    discard await b.sendMessage(response.chat.id, text, parseMode = "markdown", disableNotification = true, replyToMessageId = response.messageId)

let bot = newTeleBot(GetToken())

bot.onUpdate(updateHandler)
bot.poll(timeout=300)
