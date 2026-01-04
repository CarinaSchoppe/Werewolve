package me.carinaschoppe.werewolf.core.model.roles.subroles

import me.carinaschoppe.werewolf.core.model.roles.BaseRole
import me.carinaschoppe.werewolf.core.model.roles.Role

class Sentinel : BaseRole(Role.SENTINEL) {
    override fun performVote() {
        1
    }

    override fun performRoleAction() {
        TODO("Not yet implemented")
    }
}