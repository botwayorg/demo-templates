FROM botwayorg/botway-cli:latest AS bw

ARG DISCORD_TOKEN DISCORD_CLIENT_ID
# You can add guild ids of your servers by adding ARG SERVER_NAME_GUILD_ID

COPY . .

RUN botway docker-init

FROM nimlang/nim:alpine

ENV PACKAGES "build-dependencies build-base openssl openssl-dev musl-dev libressl-dev gcc git lld clang libsodium ffmpeg opus autoconf automake libtool m4 youtube-dl binutils zlib-dev boost boost-dev"

RUN apk update && \
	apk add --no-cache --virtual ${PACKAGES}

# To add more packages
# RUN apk add PACKAGE_NAME

COPY . .

RUN nimble install -y

RUN nim c -d:ssl ./src/main.nim

ENTRYPOINT [ "./src/main" ]
