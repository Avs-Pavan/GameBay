package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a game date.
 *
 * @property numeric The numeric representation of the date.
 * @property text The textual representation of the date.
 * @property time The time of the game.
 * @property timestamp The timestamp of the game.
 * @property isTBA Indicates if the game time is to be announced.
 * @property isTba Indicates if the game time is to be announced (duplicate).
 */
data class GameDate(
    val numeric: String,
    val text: String,
    val time: String,
    val timestamp: String,
    val isTBA: Boolean,
    val isTba: Boolean
)