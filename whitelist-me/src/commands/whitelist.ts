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

        // TODO: Add user to whitelist

        await interaction.reply("");
    }
}
