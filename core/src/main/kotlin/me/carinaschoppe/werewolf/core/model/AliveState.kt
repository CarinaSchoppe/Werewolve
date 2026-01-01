package me.carinaschoppe.werewolf.core.model

/**
 * Indicates whether a player is still participating in the current match.
 */
enum class AliveState {
    /** Player can still take turns and be targeted. */
    ALIVE,

    /** Player has been removed from the game via death or elimination. */
    DEAD
}