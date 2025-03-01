package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a Team.
 *
 * @property triCode The team's three-letter code.
 * @property fullName The full name of the team.
 * @property name The name of the team.
 * @property city The city the team is based in.
 * @property record The team's record.
 * @property wins The number of wins the team has.
 * @property losses The number of losses the team has.
 * @property winPercentage The team's win percentage.
 * @property primaryColor The primary color of the team.
 */
data class Team(
    val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String,
    val wins: String,
    val losses: String,
    val winPercentage: String,
    val primaryColor: String
)