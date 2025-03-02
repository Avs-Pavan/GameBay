package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ScheduleEntity(
    @PrimaryKey
    val id: Long = 0L,
    val defaultGameId: String = "",
    @Embedded val team: TeamEntity = TeamEntity(),
)


@Entity
data class GameSectionEntity(
    @PrimaryKey
    val heading: String = "",
    val scheduleId: Long = 0,
)


@Entity
data class GameEntity(
    @PrimaryKey val id: Long = 0,
    val gameSectionHeading: String = "",
    val week: String = "",
    val tv: String = "",
    val radio: String = "",
    val wlt: String = "",
    val gameState: String = "",
    val awayScore: String = "",
    val homeScore: String = "",
    val type: String = "",
    val dateText: String = "",
    val dateTime: String = "",
    val dateTimestamp: String = "",
    @Embedded(prefix = "opponent_") val opponent: TeamEntity = TeamEntity(),
)

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