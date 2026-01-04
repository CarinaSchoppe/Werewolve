package me.carinaschoppe.werewolf.core.model.player

import me.carinaschoppe.werewolf.core.model.roles.Actor
import me.carinaschoppe.werewolf.core.persistence.PlayerStats

class HumanPlayer(id: String, name: String, val stats: PlayerStats, roleActor: Actor) : BasePlayer(id, name, roleActor)