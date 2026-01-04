package me.carinaschoppe.werewolf.core.model.player

import me.carinaschoppe.werewolf.core.model.roles.Actor

/**
 * Abstract base type shared by all player representations.
 */
abstract class BasePlayer(val id: String, val name: String, val roleActor: Actor, state: AliveState = AliveState.ALIVE)