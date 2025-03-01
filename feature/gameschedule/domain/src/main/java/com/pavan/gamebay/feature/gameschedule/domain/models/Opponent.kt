package com.pavan.gamebay.feature.gameschedule.domain.models

// Opponent model
/**
 * Data class representing an opponent in the game schedule.
 *
 * @property triCode The three-letter code representing the opponent.
 * @property fullName The full name of the opponent.
 * @property name The name of the opponent.
 * @property city The city of the opponent.
 * @property record The record of the opponent.
 */
data class Opponent(
    val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String
)