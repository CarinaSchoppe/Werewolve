package me.carinaschoppe.werewolf.core.util

import kotlin.random.Random

class SeededRng(seed: Long) {
    private val random: Random = Random(seed)

    fun nextInt(bound: Int): Int {
        require(bound > 0) { "Bound must be positive" }
        return random.nextInt(bound)
    }

    fun nextBoolean(): Boolean = random.nextBoolean()

    fun <T> pick(options: List<T>): T {
        require(options.isNotEmpty()) { "Cannot pick from an empty list" }
        return options[nextInt(options.size)]
    }

    fun <T> shuffled(list: List<T>): List<T> = list.shuffled(random)


}
