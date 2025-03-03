package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

import androidx.compose.runtime.Immutable

/**
 * Immutable data class representing a team in the game schedule.
 *
 * @property triCode The three-letter code representing the team.
 * @property name The full name of the team.
 * @property record The current record of the team (e.g., "10-5").
 * @property wins The number of wins the team has.
 * @property losses The number of losses the team has.
 * @property teamLogoUrl The URL of the team's logo.
 */
@Immutable
data class TeamUIModel(
    val triCode: String,
    val name: String,
    val record: String,
    val wins: Int,
    val losses: Int,
    val teamLogoUrl: String,
)