package main

import (
	"fmt"
	"os"

	"github.com/abdfnx/botwaygo"
	"github.com/gempir/go-twitch-irc/v3"
	log "github.com/sirupsen/logrus"
	chatbot "github.com/vikpe/twitch-chatbot"
)

func main() {
	username := "USERNAME"
	oauth := botwaygo.GetToken()
	channel := "CHANNEL_NAME" // you can use your username as channel name
	commandPrefix := '!'

	myBot := chatbot.NewChatbot(username, oauth, channel, commandPrefix)

	// event callbacks
	myBot.OnStarted = func() { log.Info("chatbot started") }
	myBot.OnConnected = func() { log.Info("chatbot connected") }
	myBot.OnStopped = func(sig os.Signal) {
		log.Info((fmt.Sprintf("chatbot stopped (%s)", sig)))
	}

	// command handlers
	myBot.AddCommand("hello", func(cmd chatbot.Command, msg twitch.PrivateMessage) {
		myBot.Reply(msg, "world!")
	})

	myBot.AddCommand("test", func(cmd chatbot.Command, msg twitch.PrivateMessage) {
		myBot.Say(fmt.Sprintf("%s called the test command using args %s", msg.User.Name, cmd.ArgsToString()))
	})

	myBot.AddCommand("mod_only", func(cmd chatbot.Command, msg twitch.PrivateMessage) {
		if !chatbot.IsModerator(msg.User) {
			myBot.Reply(msg, "mod_only is only allowed by moderators.")
			return
		}

		myBot.Say(fmt.Sprintf("%s called the mod_only command", msg.User.Name))
	})

	myBot.AddCommand("sub_only", func(cmd chatbot.Command, msg twitch.PrivateMessage) {
		if !chatbot.IsSubscriber(msg.User) {
			myBot.Reply(msg, "sub_only is only allowed by subscribers.")
			return
		}

		myBot.Say(fmt.Sprintf("%s called the sub_only command", msg.User.Name))
	})

	log.Debug(myBot.GetCommands(", ")) // "hello, test, mod_only, sub_only"

	myBot.Start()
}
