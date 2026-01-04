package me.carinaschoppe.werewolf.core.logic

import me.carinaschoppe.werewolf.core.model.player.BasePlayer
import me.carinaschoppe.werewolf.core.util.SeededRng

class Game(val players: List<BasePlayer> = ArrayList<BasePlayer>(), val randomgenerator: SeededRng = SeededRng(System.currentTimeMillis()))