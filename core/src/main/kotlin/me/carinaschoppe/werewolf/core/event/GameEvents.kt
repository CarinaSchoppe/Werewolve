package me.carinaschoppe.werewolf.core.event

import me.carinaschoppe.werewolf.core.model.BasePlayer
import me.carinaschoppe.werewolf.core.model.Faction
import me.carinaschoppe.werewolf.core.model.GameId
import me.carinaschoppe.werewolf.core.model.Phase
import java.time.Instant

/**
 * Marker interface for all immutable facts emitted by the engine.
 *
 * Events are append-only and form the authoritative chronology used by user interfaces
 * as well as persistence layers.
 */
sealed interface GameEvent {
    val gameId: GameId
    val at: Instant
}

/** Raised whenever the game transitions to a new [Phase]. */
data class PhaseStarted(
    override val gameId: GameId,
    override val at: Instant,
    val phase: Phase
) : GameEvent

/** Simple informational log entry (system or player auth). */
data class MessagePosted(
    override val gameId: GameId,
    override val at: Instant,
    val authorId: BasePlayer?,
    val content: String
) : GameEvent

/**
 * Captures a night-time or instant kill before public acknowledgement.
 */
data class PlayerKilled(
    override val gameId: GameId,
    override val at: Instant,
    val playerId: BasePlayer,
    val reason: String
) : GameEvent

/** Records a single ballot during the voting stage. */
data class PlayerVoted(
    override val gameId: GameId,
    override val at: Instant,
    val voterId: BasePlayer,
    val targetId: BasePlayer
) : GameEvent

/** Reflects the public elimination (lynch) of a player. */
data class PlayerEliminated(
    override val gameId: GameId,
    override val at: Instant,
    val playerId: BasePlayer,
    val reason: String
) : GameEvent

/** Final event once victory conditions have been satisfied. */
data class GameEnded(
    override val gameId: GameId,
    override val at: Instant,
    val winner: Faction,
    val reason: String
) : GameEvent
