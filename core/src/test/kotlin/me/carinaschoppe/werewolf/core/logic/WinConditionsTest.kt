package me.carinaschoppe.werewolf.core.logic

import me.carinaschoppe.werewolf.core.model.BasePlayer
import me.carinaschoppe.werewolf.core.model.Faction
import me.carinaschoppe.werewolf.core.model.GameId
import me.carinaschoppe.werewolf.core.model.PlayerState
import me.carinaschoppe.werewolf.core.model.Role
import me.carinaschoppe.werewolf.core.state.GameState
import me.carinaschoppe.werewolf.core.util.SeededRng
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class WinConditionsTest {

    private fun createState(players: List<PlayerState>, seed: Long = 1L): GameState =
        GameState.create(GameId("test"), players, SeededRng(seed))

    @Test
    fun `villagers win when no werewolves remain`() {
        val players = listOf(
            PlayerState(BasePlayer("P1"), "Alice", Role.VILLAGER),
            PlayerState(BasePlayer("P2"), "Bob", Role.SEER),
            PlayerState(BasePlayer("P3"), "Cara", Role.DOCTOR)
        )

        val result = checkWin(createState(players))

        assertEquals(Faction.VILLAGERS, result?.winner)
    }

    @Test
    fun `werewolves win on parity`() {
        val players = listOf(
            PlayerState(BasePlayer("P1"), "Alice", Role.VILLAGER),
            PlayerState(BasePlayer("P2"), "Bob", Role.WEREWOLF)
        )

        val result = checkWin(createState(players))

        assertEquals(Faction.WEREWOLVES, result?.winner)
    }

    @Test
    fun `no winner when both factions still contesting`() {
        val players = listOf(
            PlayerState(BasePlayer("P1"), "Alice", Role.VILLAGER),
            PlayerState(BasePlayer("P2"), "Bob", Role.WEREWOLF),
            PlayerState(BasePlayer("P3"), "Cara", Role.VILLAGER)
        )

        val result = checkWin(createState(players))

        assertNull(result)
    }
}
