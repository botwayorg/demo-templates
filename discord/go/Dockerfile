FROM botwayorg/botway-cli:latest AS bw

ARG DISCORD_TOKEN DISCORD_CLIENT_ID
# You can add guild ids of your servers by adding ARG SERVER_NAME_GUILD_ID

COPY . .

RUN botway docker-init

FROM golang:alpine

ENV PACKAGES "build-dependencies build-base gcc git binutils openssl-dev zlib-dev boost boost-dev"

RUN apk update && \
	apk add --no-cache --virtual ${PACKAGES}

# To add more packages
# RUN apk add PACKAGE_NAME

COPY --from=bw /root/.botway /root/.botway

WORKDIR /app/

COPY . .

RUN go mod tidy

RUN go build -o bot ./src/main.go

ENTRYPOINT ["./bot"]
