package com.pavan.gamebay.feature.gameschedule.domain.models


/**
 * Data class representing a game schedule.
 *
 * @property team The team associated with the schedule.
 * @property defaultGameId The default game ID.
 * @property gameSection The list of game sections.
 */
data class Schedule(
    val team: Team,
    val defaultGameId: Long,
    val gameSection: List<GameSection>,
)
