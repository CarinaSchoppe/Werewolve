package me.carinaschoppe.werewolf.core.setup.playermanagement

import me.carinaschoppe.werewolf.core.model.roles.BaseRole
import me.carinaschoppe.werewolf.core.model.roles.Role

class RoleFactory {


    fun createRole(role: Role): BaseRole {
        return role.clazz.getConstructor().newInstance()
    }
}