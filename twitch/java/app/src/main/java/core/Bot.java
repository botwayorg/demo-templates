package core;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;

import core.features.ChannelNotificationOnDonation;
import core.features.ChannelNotificationOnFollow;
import core.features.ChannelNotificationOnSubscription;
import core.features.WriteChannelChatToConsole;

public class Bot {
    /**
     * Twitch4J API
     */
    private TwitchClient twitchClient;

    /**
     * Constructor
     */
    public Bot() {
        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        // region Auth
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                botway.BotwayKt.GetToken()
        );
        // endregion

        // region TwitchClient
        twitchClient = clientBuilder
                .withClientId(botway.BotwayKt.GetClientID())
                .withClientSecret(botway.BotwayKt.GetClientSecret())
                .withEnableHelix(true)
                /*
                 * Chat Module
                 * Joins irc and triggers all chat based events (viewer join/leave/sub/bits/gifted subs/...)
                 */
                .withChatAccount(credential)
                .withEnableChat(true)
                /*
                 * GraphQL has a limited support
                 * Don't expect a bunch of features enabling it
                 */
                .withEnableGraphQL(true)
                .build();
        // endregion
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {
		SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        // Register Event-based features
        ChannelNotificationOnDonation channelNotificationOnDonation = new ChannelNotificationOnDonation(eventHandler);
        ChannelNotificationOnFollow channelNotificationOnFollow = new ChannelNotificationOnFollow(eventHandler);
        ChannelNotificationOnSubscription channelNotificationOnSubscription = new ChannelNotificationOnSubscription(eventHandler);
		WriteChannelChatToConsole writeChannelChatToConsole = new WriteChannelChatToConsole(eventHandler);
    }

    public void start() {
        // Connect to all channels
        twitchClient.getChat().joinChannel("CHANNEL_NAME"); // you can use your username as channel name
    }
}
