package me.carinaschoppe.werewolf.core.model.roles

import me.carinaschoppe.werewolf.core.model.roles.subroles.*

/**
 * Comprehensive catalogue of roles supported by the engine skeleton.
 *
 * Each role carries a presentation name, faction and a free-form description to aid UIs.
 */
enum class Role(val displayName: String, val faction: Faction, val description: String, private val creator: () -> BaseRole) {
    WEREWOLF("Werewolf", Faction.WEREWOLVES, "Eliminates one villager each night with fellow wolves", ::Werewolf),
    SEER("Seer", Faction.VILLAGERS, "May inspect a player's alignment each night", ::Seer),
    DOCTOR("Doctor", Faction.VILLAGERS, "Protects a player from being killed at night", ::Doctor),
    HUNTER("Hunter", Faction.VILLAGERS, "Can take someone down with them when eliminated", ::Hunter),
    WITCH("Witch", Faction.VILLAGERS, "Has one heal potion and one poison potion", ::Witch),
    BODYGUARD("Bodyguard", Faction.VILLAGERS, "Shields a target; dies instead if target is attacked", ::BodyGuard),
    SENTINEL("Sentinel", Faction.VILLAGERS, "Can fortify a player to block night actions", ::Sentinel),
    AURA_SEER("Aura Seer", Faction.VILLAGERS, "Detects whether a player is harmful or benign", ::AuraSeer),
    ORACLE("Oracle", Faction.VILLAGERS, "Reveals facts about a player's role upon elimination", ::Oracle),
    GUARDIAN_ANGEL("Guardian Angel", Faction.VILLAGERS, "Protects a chosen player for multiple nights", ::GuardianAngel),
    CUPID("Cupid", Faction.VILLAGERS, "Links two lovers whose fates are entwined", ::Cupid),
    ALPHA_WOLF("Alpha Wolf", Faction.WEREWOLVES, "Leads the wolf pack and may convert others", ::AlphaWolf),
    SORCERER("Sorcerer", Faction.WEREWOLVES, "Assists wolves by countering seers", ::Sorcerer),
    WOLF_SHAMAN("Wolf Shaman", Faction.WEREWOLVES, "Blesses wolves and curses villagers", ::WolfShaman),
    BERSERKER("Berserker", Faction.WEREWOLVES, "Can make an additional attack when enraged", ::Berserker),
    CULTIST("Cultist", Faction.NEUTRAL, "Wins by surviving until only cult remains", ::Cultist),
    TRICKSTER("Trickster", Faction.NEUTRAL, "Sows chaos with unpredictable abilities", ::Trickster),
    ILLUSIONIST("Illusionist", Faction.NEUTRAL, "Masks true roles and spreads misinformation", ::Illusionist);

    fun create(): BaseRole = creator()
}