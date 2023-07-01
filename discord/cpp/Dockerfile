FROM botwayorg/botway-cli:latest AS bw

ARG DISCORD_TOKEN DISCORD_CLIENT_ID
# You can add guild ids of your servers by adding ARG SERVER_NAME_GUILD_ID

COPY . .

RUN botway docker-init

FROM brainboxdotcc/dpp:latest

WORKDIR /usr/src/cpp

COPY --from=bw /root/.botway /root/.botway

COPY . .

WORKDIR /usr/src/cpp/build

RUN cmake ..

RUN make -j$(nproc)

ENTRYPOINT [ "/usr/src/cpp/build/cpp" ]
