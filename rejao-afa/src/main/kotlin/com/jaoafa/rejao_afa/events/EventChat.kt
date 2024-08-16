package com.jaoafa.rejao_afa.events

import com.jaoafa.rejao_afa.getRank
import com.jaoafa.rejao_afa.getVote
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventChat : Listener {
    val colors = mapOf(
        "GRAY" to NamedTextColor.GRAY,
        "WHITE" to NamedTextColor.WHITE,
        "DARK_BLUE" to NamedTextColor.DARK_BLUE,
        "BLUE" to NamedTextColor.BLUE,
        "AQUA" to NamedTextColor.AQUA,
        "DARK_AQUA" to NamedTextColor.DARK_AQUA,
        "DARK_GREEN" to NamedTextColor.DARK_GREEN,
        "GREEN" to NamedTextColor.GREEN,
        "YELLOW" to NamedTextColor.YELLOW,
        "GOLD" to NamedTextColor.GOLD,
        "RED" to NamedTextColor.RED,
        "DARK_RED" to NamedTextColor.DARK_RED,
        "DARK_PURPLE" to NamedTextColor.DARK_PURPLE,
        "LIGHT_PURPLE" to NamedTextColor.LIGHT_PURPLE
    )

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val player = event.player
        val colorString = player.getVote()?.customColor
        val customColor = colorString.let {
            if (it == null) null
            else if (it.startsWith("#")) TextColor.fromHexString(it)
            else colors[it.uppercase()]
        }

        println(player.getRank())
        val rankColor = colors.values.toList()[player.getRank()]

        val color = customColor ?: rankColor
        val dateString = LocalDateTime.now().minusHours(3).format(DateTimeFormatter.ofPattern("HH:mm:ss"))

        event.renderer { _, displayName, message, _ ->
            Component.text().append(
                Component.text("[$dateString]", NamedTextColor.GRAY),
                Component.text("■", color),
                displayName,
                Component.text(": "),
                message
            ).build()
        }
    }
}