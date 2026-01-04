package me.carinaschoppe.werewolf.core.persistence

import java.util.*

data class PlayerStats(val gamesPlayed: Int, val wins: Int, val losses: Int, val points: Int, val lastPlayed: Date)