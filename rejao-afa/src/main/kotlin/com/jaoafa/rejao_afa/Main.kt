package com.jaoafa.rejao_afa

import com.jaoafa.rejao_afa.events.EventChat
import com.jaoafa.rejao_afa.events.EventJoinLeft
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {

    fun processCSV(line: String) = line.split(",").map {
        it.removePrefix("\"").removeSuffix("\"")
    }

    override fun onEnable() {
        val votePath = System.getenv("VOTE_PATH") ?: throw IllegalStateException("VOTE_PATH is not set")
        val monoVotePath = System.getenv("MONO_VOTE_PATH") ?: throw IllegalStateException("MONO_VOTE_PATH is not set")

        val voteCsvFile = File(votePath)
        val monoVoteCsvFile = File(monoVotePath)

        if (!voteCsvFile.exists())
            throw IllegalStateException("Vote CSV file not found")

        if (!monoVoteCsvFile.exists())
            throw IllegalStateException("Mono Vote CSV file not found")

        val voteCsv = File(votePath).readText().lines().map(::processCSV).filter { it.size == 8 }
        val monoVoteCsv = File(monoVotePath).readText().lines().map(::processCSV).filter { it.size == 9 }

        val votePlayers = voteCsv.map {
            it[1] /* Minecraft ID */
        }
        val monoVotePlayers = monoVoteCsv.map {
            it[1] /* Minecraft ID */
        }

        val players = (votePlayers + monoVotePlayers).toSet()

        players.forEach {
            val monoVote = monoVoteCsv.firstOrNull { row -> row[1] == it }
            val vote = voteCsv.firstOrNull { row -> row[1] == it }

            val voteData = Vote(
                id = it,
                count = vote?.get(3)?.toIntOrNull() ?: 0,
                monoCount = monoVote?.get(3)?.toIntOrNull() ?: 0,
                customString = vote?.get(4),
                customColor = monoVote?.get(4)
            )

            votes.add(voteData)
        }

        listOf(
            EventJoinLeft(),
            EventChat()
        ).forEach {
            Bukkit.getServer().pluginManager.registerEvents(it, this)
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
