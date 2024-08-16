package com.jaoafa.rejao_afa

import org.bukkit.entity.Player

data class Vote(
    val id: String,
    val count: Int,
    val monoCount: Int,
    val customString: String?,
    val customColor: String?
)

val votes = mutableListOf<Vote>()

fun Player.getVote() = votes.find { name == it.id }

fun Player.getVoteCount() = getVote()?.count ?: 0

fun Player.getRank(): Int {
    val count = getVoteCount()

    return when {
        count == 0 -> 0
        count <= 5 -> 1
        count >= 160 -> 13
        else -> ((count - 6) / 14) + 2
    }
}