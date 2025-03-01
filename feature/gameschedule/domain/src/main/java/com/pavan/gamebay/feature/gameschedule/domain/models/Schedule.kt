package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Represents the schedule for a team.
 *
 * @property team The team associated with the schedule.
 * @property defaultGameId The default game ID.
 * @property gameSection The list of game sections.
 * @property filters The list of filters applied to the schedule.
 * @property yinzNode The YinzNode associated with the schedule.
 */
data class Schedule(
    val team: Team,
    val defaultGameId: Long,
    val gameSection: List<GameSection>,
    val filters: List<Filter>,
    val yinzNode: YinzNode
)