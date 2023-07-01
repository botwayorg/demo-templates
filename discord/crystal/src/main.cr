require "discordcr"
require "botwaycr"

bw = Botwaycr::BW.new

client = Discord::Client.new(token: "Bot " + bw.get_token, client_id: bw.get_app_id.to_u64)

client.on_message_create do |payload|
  if payload.content.starts_with? "ping"
    client.create_message(payload.channel_id, "Pong!")
  end
end

client.run
