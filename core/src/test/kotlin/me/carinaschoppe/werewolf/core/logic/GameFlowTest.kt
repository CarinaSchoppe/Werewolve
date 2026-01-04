package me.carinaschoppe.werewolf.core.logic

import me.carinaschoppe.werewolf.core.event.PlayerEliminated
import me.carinaschoppe.werewolf.core.model.GameId
import me.carinaschoppe.werewolf.core.model.PlayerState
import me.carinaschoppe.werewolf.core.model.player.AliveState
import me.carinaschoppe.werewolf.core.model.player.BasePlayer
import me.carinaschoppe.werewolf.core.model.roles.Phase
import me.carinaschoppe.werewolf.core.model.roles.Role
import me.carinaschoppe.werewolf.core.state.GameState
import me.carinaschoppe.werewolf.core.util.SeededRng
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Instant

class GameFlowTest {

    private fun basePlayers(): List<PlayerState> = listOf(
        PlayerState(BasePlayer("P1"), "Alice", Role.VILLAGER),
        PlayerState(BasePlayer("P2"), "Bob", Role.VILLAGER),
        PlayerState(BasePlayer("P3"), "Cara", Role.WEREWOLF),
        PlayerState(BasePlayer("P4"), "Dan", Role.SEER)
    )

    private fun createState(seed: Long = 1L): GameState =
        GameState.create(GameId("test"), basePlayers(), SeededRng(seed))

    @Test
    fun `phase order follows lobby to night to day to voting`() {
        var state = createState()
        assertEquals(Phase.LOBBY, state.phase)

        state = advancePhase(state, Instant.EPOCH)
        assertEquals(Phase.NIGHT, state.phase)
        assertEquals(1, state.nightNumber)

        state = advancePhase(state, Instant.EPOCH.plusSeconds(1))
        assertEquals(Phase.DAY, state.phase)
        assertEquals(1, state.dayNumber)

        state = advancePhase(state, Instant.EPOCH.plusSeconds(2))
        assertEquals(Phase.VOTING, state.phase)

        state = recordVote(state, BasePlayer("P1"), BasePlayer("P3"))
        state = advancePhase(state, Instant.EPOCH.plusSeconds(3))
        assertEquals(Phase.NIGHT, state.phase)
        assertEquals(2, state.nightNumber)
        assertTrue(state.currentVotes.isEmpty())
    }

    @Test
    fun `dead players cannot vote`() {
        var state = createState()
        state = killPlayer(state, BasePlayer("P1"), "test kill", Instant.EPOCH)
        val deadPlayer = state.player(BasePlayer("P1"))
        assertEquals(AliveState.DEAD, deadPlayer.alive)

        assertThrows<IllegalArgumentException> {
            recordVote(state, BasePlayer("P1"), BasePlayer("P2"))
        }
    }

    @Test
    fun `vote tie is resolved deterministically by rng seed`() {
        fun resolveWithSeed(seed: Long): BasePlayer {
            var state = createState(seed).withPhase(Phase.VOTING, Instant.EPOCH)
            state = recordVote(state, BasePlayer("P1"), BasePlayer("P2"), Instant.EPOCH)
            state = recordVote(state, BasePlayer("P3"), BasePlayer("P4"), Instant.EPOCH)
            state = resolveVote(state, Instant.EPOCH.plusSeconds(1))
            val elimination = state.events.filterIsInstance<PlayerEliminated>().last()
            return elimination.playerId
        }

        val first = resolveWithSeed(1234L)
        val second = resolveWithSeed(1234L)
        assertEquals(first, second)
    }
}
