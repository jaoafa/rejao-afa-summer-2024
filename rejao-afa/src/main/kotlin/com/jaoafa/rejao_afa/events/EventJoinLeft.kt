package com.jaoafa.rejao_afa.events

import com.jaoafa.rejao_afa.votes
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import kotlin.math.floor

class EventJoinLeft : Listener {
    private val words = listOf(
        "the New Generation",
        "- Super",
        "Hyper",
        "Ultra",
        "Extreme",
        "Insane",
        "Gigantic",
        "Epic",
        "Amazing",
        "Beautiful",
        "Special",
        "Swag",
        "Lunatic",
        "Exotic",
        "God",
        "Hell",
        "Heaven",
        "Mega",
        "Giga",
        "Tera",
        "Refined",
        "Sharp",
        "Strong",
        "Muscle",
        "Macho",
        "Bomber",
        "Blazing",
        "Frozen",
        "Legendary",
        "Mystical",
        "Tactical",
        "Critical",
        "Overload",
        "Overclock",
        "Fantastic",
        "Criminal",
        "Primordial",
        "Genius",
        "Great",
        "Perfect",
        "Fearless",
        "Ruthless",
        "Bold",
        "Void",
        "Millenium",
        "Exact",
        "Really",
        "Certainty",
        "Infernal",
        "Ender",
        "World",
        "Mad",
        "Crazy",
        "Wrecked",
        "Elegant",
        "Expensive",
        "Rich",
        "Radioactive",
        "Automatic",
        "Honest",
        "Cosmic",
        "Galactic",
        "Dimensional",
        "Sinister",
        "Evil",
        "Abyssal",
        "Hallowed",
        "Holy",
        "Sacred",
        "Omnipotent"
    )

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        event.joinMessage(player.getJoinMessage())
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onLeft(event: PlayerQuitEvent) {
        val player = event.player
        val message = "${player.name} left the game."

        event.quitMessage(Component.text(message, NamedTextColor.YELLOW))
    }

    private fun Player.getJoinTitle(): String? {
        val count = getVoteCount()

        return if (count < 20) {
            null
        } else if (count < 24) {
            "VIP"
        } else {
            val limit = floor((count / 4.0) - 5).toInt()
            val customString = getVote()?.customString
            val titles = words.take(limit).toMutableList()

            if (customString != null) titles.add(customString)
            titles.add("VIP")

            return titles.joinToString(" ")
        }
    }

    private fun Player.getJoinMessage(): Component {
        val title = getJoinTitle()

        val message = if (title == null) {
            "$name joined the game."
        } else {
            "$name, $title (${getVoteCount()}) joined the game."
        }

        return Component.text(message, NamedTextColor.YELLOW)
    }

    private fun Player.getVote() = votes.find { name == it.id }

    private fun Player.getVoteCount() = getVote()?.count ?: 0
}