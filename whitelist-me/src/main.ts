import {dirname, importx} from "@discordx/importer";
import type {Interaction, Message} from "discord.js";
import {IntentsBitField} from "discord.js";
import {Client} from "discordx";
import * as dotenv from "dotenv";

export const bot = new Client({
    botGuilds: [(client) => client.guilds.cache.map((guild) => guild.id)],
    intents: [
        IntentsBitField.Flags.Guilds,
        IntentsBitField.Flags.GuildMembers,
        IntentsBitField.Flags.GuildMessages,
        IntentsBitField.Flags.GuildMessageReactions,
        IntentsBitField.Flags.GuildVoiceStates,
        IntentsBitField.Flags.MessageContent,
    ],
    silent: false
});

bot.once("ready", () => {
    void bot.initApplicationCommands();
    console.log("Bot started");
});

bot.on("interactionCreate", (interaction: Interaction) => {
    bot.executeInteraction(interaction);
});

bot.on("messageCreate", (message: Message) => {
    void bot.executeCommand(message);
});

async function run() {
    dotenv.config();

    await importx(`${dirname(import.meta.url)}/{events,commands}/**/*.{ts,js}`);

    if (!process.env.BOT_TOKEN)
        throw Error("Could not find BOT_TOKEN in your environment");

    await bot.login(process.env.BOT_TOKEN);
}

void run();
