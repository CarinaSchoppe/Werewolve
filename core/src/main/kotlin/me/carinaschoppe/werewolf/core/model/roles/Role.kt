package me.carinaschoppe.werewolf.core.model.roles

import me.carinaschoppe.werewolf.core.model.roles.subroles.*

/**
 * Comprehensive catalogue of roles supported by the engine skeleton.
 *
 * Each role carries a presentation name, faction and a free-form description to aid UIs.
 */
enum class Role(val displayName: String, val faction: Faction, val description: String, val clazz: Class<out BaseRole>) {
    WEREWOLF("Werewolf", Faction.WEREWOLVES, "Eliminates one villager each night with fellow wolves", Werewolf::class.java),
    SEER("Seer", Faction.VILLAGERS, "May inspect a player's alignment each night", Seer::class.java),
    DOCTOR("Doctor", Faction.VILLAGERS, "Protects a player from being killed at night", Doctor::class.java),
    HUNTER("Hunter", Faction.VILLAGERS, "Can take someone down with them when eliminated", Hunter::class.java),
    WITCH("Witch", Faction.VILLAGERS, "Has one heal potion and one poison potion", Witch::class.java),
    BODYGUARD("Bodyguard", Faction.VILLAGERS, "Shields a target; dies instead if target is attacked", BodyGuard::class.java),
    SENTINEL("Sentinel", Faction.VILLAGERS, "Can fortify a player to block night actions", Sentinel::class.java),
    AURA_SEER("Aura Seer", Faction.VILLAGERS, "Detects whether a player is harmful or benign", AuraSeer::class.java),
    ORACLE("Oracle", Faction.VILLAGERS, "Reveals facts about a player's role upon elimination", Oracle::class.java),
    GUARDIAN_ANGEL("Guardian Angel", Faction.VILLAGERS, "Protects a chosen player for multiple nights", GuardianAngel::class.java),
    CUPID("Cupid", Faction.VILLAGERS, "Links two lovers whose fates are entwined", Cupid::class.java),
    ALPHA_WOLF("Alpha Wolf", Faction.WEREWOLVES, "Leads the wolf pack and may convert others", AlphaWolf::class.java),
    SORCERER("Sorcerer", Faction.WEREWOLVES, "Assists wolves by countering seers", Sorcerer::class.java),
    WOLF_SHAMAN("Wolf Shaman", Faction.WEREWOLVES, "Blesses wolves and curses villagers", WolfShaman::class.java),
    BERSERKER("Berserker", Faction.WEREWOLVES, "Can make an additional attack when enraged", Berserker::class.java),
    CULTIST("Cultist", Faction.NEUTRAL, "Wins by surviving until only cult remains", Cultist::class.java),
    TRICKSTER("Trickster", Faction.NEUTRAL, "Sows chaos with unpredictable abilities", Trickster::class.java),
    ILLUSIONIST("Illusionist", Faction.NEUTRAL, "Masks true roles and spreads misinformation", Illusionist::class.java)
}