package core

import dev.kord.core.*
import dev.kord.core.entity.*
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.gateway.*
import botway.*

suspend fun main() {
    val kord = Kord(GetToken())
    val pingPong = ReactionEmoji.Unicode("\uD83C\uDFD3")

    kord.on<MessageCreateEvent> {
        if (message.content != "ping") return@on

        val response = message.channel.createMessage("Pong!")
        response.addReaction(pingPong)
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
