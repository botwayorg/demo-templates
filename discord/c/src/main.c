#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "botway.h"
#include "concord/discord.h"

void print_usage(void)
{
    printf("\n\nThis bot demonstrates a simple ping-pong response.\n"
           "1. Type 'pong' in chat\n"
           "2. Type 'ping' in chat\n");
}

void on_ready(struct discord *client, const struct discord_ready *event)
{
    log_info("Bot succesfully connected to Discord as %s#%s!",
             event->user->username, event->user->discriminator);
}

void on_ping(struct discord *client, const struct discord_message *event)
{
    if (event->author->bot)
        return;

    struct discord_create_message params = {.content = "pong"};
    discord_create_message(client, event->channel_id, &params, NULL);
}

void on_pong(struct discord *client, const struct discord_message *event)
{
    if (event->author->bot)
        return;

    struct discord_create_message params = {.content = "ping"};
    discord_create_message(client, event->channel_id, &params, NULL);
}

int main(int argc, char *argv[])
{
    const char *botway_dir = path_join(getenv("HOME"), ".botway");
    const char *config_file = path_join(botway_dir, "botway-c-config.json");

    ccord_global_init();
    struct discord *client = discord_config_init(config_file);

    discord_set_on_ready(client, &on_ready);
    discord_set_on_command(client, "ping", &on_ping);
    discord_set_on_command(client, "pong", &on_pong);

    print_usage();

    discord_run(client);

    discord_cleanup(client);
    ccord_global_cleanup();
}
