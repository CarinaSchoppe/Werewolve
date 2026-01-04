package me.carinaschoppe.werewolf.core.setup.playermanagement

import me.carinaschoppe.werewolf.core.logic.Game
import me.carinaschoppe.werewolf.core.model.player.BasePlayer
import me.carinaschoppe.werewolf.core.model.roles.Role


class PlayerRollManagement(game: Game) {

    val shuffled: MutableList<Role> = mutableListOf()

    val roleFactory = RoleFactory()

    init {
        shuffled.addAll(Role.entries)
    }


    fun assignRole(player: BasePlayer) {
        val role = shuffled.removeFirstOrNull() ?: throw UnsupportedOperationException("There is no shuffled Role left to assign")
        player.role = roleFactory.createRole(role)

    }

}