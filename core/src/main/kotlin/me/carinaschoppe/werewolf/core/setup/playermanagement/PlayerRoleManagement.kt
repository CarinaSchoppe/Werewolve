package me.carinaschoppe.werewolf.core.setup.playermanagement

import me.carinaschoppe.werewolf.core.model.player.BasePlayer
import me.carinaschoppe.werewolf.core.model.roles.Role

/**
 * Manages the assignment of roles to players.
 */
class PlayerRoleManagement(
    private val roleFactory: RoleFactory = RoleFactory(),
    private val roles: List<Role> = Role.entries
) {

    private val shuffled: MutableList<Role> = roles.shuffled().toMutableList()

    /**
     * Assigns a role to the given player.
     *
     * @param player the player to assign a role to
     * @throws UnsupportedOperationException if there are no more roles to assign
     */
    fun assignRole(player: BasePlayer) {
        val role = shuffled.removeFirstOrNull() ?: throw UnsupportedOperationException("There is no shuffled Role left to assign")
        player.role = roleFactory.createRole(role)
    }
}