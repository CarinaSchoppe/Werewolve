package me.carinaschoppe.werewolf.core.model.player

import me.carinaschoppe.werewolf.core.model.roles.BaseRole
import me.carinaschoppe.werewolf.core.persistence.PlayerStats

class HumanPlayer(id: String, name: String, val stats: PlayerStats, role: BaseRole) : BasePlayer(id, name, role)