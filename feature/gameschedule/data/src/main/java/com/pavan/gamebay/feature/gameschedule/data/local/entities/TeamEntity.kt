package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a team in the game schedule.
 *
 * @property triCode The team's three-letter code.
 * @property fullName The full name of the team.
 * @property name The short name of the team.
 * @property city The city the team is based in.
 * @property record The team's record in the format "wins-losses".
 * @property wins The number of wins the team has.
 * @property losses The number of losses the team has.
 * @property winPercentage The win percentage of the team.
 * @property primaryColor The primary color associated with the team.
 */
@Entity
data class TeamEntity(
    @PrimaryKey val triCode: String = "",
    val fullName: String = "",
    val name: String = "",
    val city: String = "",
    val record: String = "",
    val wins: Int = 0,
    val losses: Int = 0,
    val winPercentage: Double = 0.0,
    val primaryColor: String = ""
)