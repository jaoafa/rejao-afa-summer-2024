package com.jaoafa.rejao_afa

data class Vote(
    val id: String,
    val count: Int,
    val monoCount: Int,
    val customString: String?,
    val customColor: String?
)

val votes = mutableListOf<Vote>()