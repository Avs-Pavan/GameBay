package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a game date.
 *
 * @property dateText A string representation of the date.
 * @property dateTime A string representation of the date and time.
 */
data class GameDate(
    val dateText: String,
    val dateTime: String,
)