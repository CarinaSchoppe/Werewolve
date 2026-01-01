package me.carinaschoppe.werewolf.core.model


/**
 * Comprehensive catalogue of roles supported by the engine skeleton.
 *
 * Each role carries a presentation name, faction and a free-form description to aid UIs.
 */
enum class Role(val displayName: String, val faction: Faction, val description: String) {
    VILLAGER("Villager", Faction.VILLAGERS, "Ordinary town member with no special ability"),
    WEREWOLF("Werewolf", Faction.WEREWOLVES, "Eliminates one villager each night with fellow wolves"),
    SEER("Seer", Faction.VILLAGERS, "May inspect a player's alignment each night"),
    DOCTOR("Doctor", Faction.VILLAGERS, "Protects a player from being killed at night"),
    HUNTER("Hunter", Faction.VILLAGERS, "Can take someone down with them when eliminated"),
    WITCH("Witch", Faction.VILLAGERS, "Has one heal potion and one poison potion"),
    BODYGUARD("Bodyguard", Faction.VILLAGERS, "Shields a target; dies instead if target is attacked"),
    SENTINEL("Sentinel", Faction.VILLAGERS, "Can fortify a player to block night actions"),
    AURA_SEER("Aura Seer", Faction.VILLAGERS, "Detects whether a player is harmful or benign"),
    ORACLE("Oracle", Faction.VILLAGERS, "Reveals facts about a player's role upon elimination"),
    GUARDIAN_ANGEL("Guardian Angel", Faction.VILLAGERS, "Protects a chosen player for multiple nights"),
    CUPID("Cupid", Faction.VILLAGERS, "Links two lovers whose fates are entwined"),
    ALPHA_WOLF("Alpha Wolf", Faction.WEREWOLVES, "Leads the wolf pack and may convert others"),
    SORCERER("Sorcerer", Faction.WEREWOLVES, "Assists wolves by countering seers"),
    WOLF_SHAMAN("Wolf Shaman", Faction.WEREWOLVES, "Blesses wolves and curses villagers"),
    BERSERKER("Berserker", Faction.WEREWOLVES, "Can make an additional attack when enraged"),
    CULTIST("Cultist", Faction.NEUTRAL, "Wins by surviving until only cult remains"),
    TRICKSTER("Trickster", Faction.NEUTRAL, "Sows chaos with unpredictable abilities"),
    ILLUSIONIST("Illusionist", Faction.NEUTRAL, "Masks true roles and spreads misinformation")
}



