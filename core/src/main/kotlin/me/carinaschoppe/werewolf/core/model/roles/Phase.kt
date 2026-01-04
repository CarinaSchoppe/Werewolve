package me.carinaschoppe.werewolf.core.model.roles


/**
 * Discrete phases that the state machine iterates through during a round.
 */
enum class Phase {
    /** Waiting room while roles are assigned and players connect. */
    LOBBY,

    /** Night actions resolve in hidden order. */
    NIGHT,

    /** Open discussion period for villagers and wolves alike. */
    DAY,

    /** Public vote that culminates in a lynch (if any). */
    VOTING,

    /** Terminal state once a faction satisfies its victory requirement. */
    ENDED
}
