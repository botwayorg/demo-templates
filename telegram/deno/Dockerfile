FROM botwayorg/botway-cli:latest AS bw

ARG TELEGRAM_TOKEN

COPY . .

RUN botway docker-init

FROM denoland/deno:alpine

ENV PACKAGES "build-dependencies build-base gcc git ffmpeg curl binutils openssl-dev zlib-dev boost boost-dev"

RUN apk update && \
	apk add --no-cache --virtual ${PACKAGES}

# To add more packages
# RUN apk add PACKAGE_NAME

COPY --from=bw /root/.botway /root/.botway

COPY . .

ENTRYPOINT ["deno", "run", "--allow-all", "main.ts"]
