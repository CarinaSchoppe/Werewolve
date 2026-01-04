package me.carinaschoppe.werewolf

import me.carinaschoppe.werewolf.core.model.roles.Role
import me.carinaschoppe.werewolf.core.setup.playermanagement.RoleFactory

fun main() {

    val roleFactory = RoleFactory()
    roleFactory.createRole(Role.ALPHA_WOLF)
}