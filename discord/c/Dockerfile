FROM botwayorg/botway-cli:latest AS bw

ARG DISCORD_TOKEN DISCORD_CLIENT_ID
# You can add guild ids of your servers by adding ARG SERVER_NAME_GUILD_ID

COPY . .

RUN botway docker-init

RUN botway c-init

FROM botwayorg/concord

# To add more packages
# RUN apk add PACKAGE_NAME

COPY --from=bw /root/.botway /root/.botway

COPY . .

RUN gcc src/main.c -o bot -pthread -ldiscord -lcurl

ENTRYPOINT [ "./bot" ]
