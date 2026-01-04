package me.carinaschoppe.werewolf.core.model.roles

/**
 * High-level alignment bucket used for win-condition resolution.
 */
enum class Faction {
    /** Cooperative town-aligned roles. */
    VILLAGERS,

    /** Classic werewolf team members. */
    WEREWOLVES,

    /** Lone-wolf or third-party roles that pursue unique goals. */
    NEUTRAL
}