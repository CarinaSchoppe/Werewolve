package me.carinaschoppe.werewolf.core.model.player

import me.carinaschoppe.werewolf.core.model.roles.BaseRole

/**
 * Abstract base type shared by all player representations.
 */
abstract class BasePlayer(val id: String, val name: String, var role: BaseRole, state: AliveState = AliveState.ALIVE)