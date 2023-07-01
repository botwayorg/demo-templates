<?php

namespace MyBot;

include __DIR__ . "/../vendor/autoload.php";
include "botway.php";

use SergiX44\Nutgram\Nutgram;

$botConfig = new Botway();
$bot = new Nutgram($botConfig->GetToken());

$bot->onCommand("start", function (Nutgram $bot) {
    $bot->sendMessage("Ciao!");
});

$bot->onText("My name is {name}", function (Nutgram $bot, string $name) {
    $bot->sendMessage("Hi $name");
});

$bot->run();
