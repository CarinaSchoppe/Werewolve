package me.carinaschoppe.werewolf.core.model

// Player.kt

data class Player(
    override val id: String,
    override val name: String,
    val role: Role,
    val stats: PlayerStats
) : BasePlayer()