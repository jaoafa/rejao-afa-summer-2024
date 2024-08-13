import type {CommandInteraction} from "discord.js";
import {ApplicationCommandOptionType} from "discord.js";
import {Discord, Slash, SlashOption} from "discordx";

@Discord()
export class Whitelist {
    @Slash({
        description: "🫵 あなたをホワイトリストに追加します。"
    })
    async whitelist(
        @SlashOption({
            description: "あなたの Minecraft ID",
            name: "id",
            required: true,
            type: ApplicationCommandOptionType.String,
        }) id: string,
        interaction: CommandInteraction,
    ): Promise<void> {
        const response = await fetch(`https://api.mojang.com/users/profiles/minecraft/${id}`);

        if (!response.ok) {
            await interaction.reply(`Minecraft ID "${id}" は存在しません。`);
            return;
        }

        const port = process.env.WHITELIST_ME_PORT || 3020;

        const result = await fetch(`http://minecraft:${port}/${id}`);

        const message = await result.text()

        if (result.ok && message === "added") {
            await interaction.reply(`:white_check_mark: \`${id}\` をホワイトリストに追加しました。`);
        } else {
            await interaction.reply(`:x: \`${id}\` をホワイトリストに追加できませんでした。`);
        }
    }
}
