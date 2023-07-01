FROM botwayorg/botway-cli:latest AS bw

ARG DISCORD_TOKEN DISCORD_CLIENT_ID
# You can add guild ids of your servers by adding ARG SERVER_NAME_GUILD_ID

COPY . .

RUN botway docker-init

FROM dart:stable

COPY --from=bw /root/.botway /root/.botway

COPY . .

RUN dart pub get

RUN dart compile exe src/main.dart -o bot

ENTRYPOINT ["./bot"]
