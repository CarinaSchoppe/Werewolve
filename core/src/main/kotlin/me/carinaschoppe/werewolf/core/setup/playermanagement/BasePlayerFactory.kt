package me.carinaschoppe.werewolf.core.setup.playermanagement

import me.carinaschoppe.werewolf.core.model.player.BasePlayer

interface BasePlayerFactory {

    fun generatePlayer(): BasePlayer
}