package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class ScheduleEntity(
    @PrimaryKey val defaultGameId: String,
    @Embedded val team: TeamEntity?,
    @Relation(
        parentColumn = "defaultGameId",
        entityColumn = "scheduleId"
    )
    val gameSections: List<GameSectionEntity> = emptyList()
)


@Entity
data class GameSectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val heading: String?,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameSectionId"
    )
    val games: List<GameEntity> = emptyList()
)


@Entity
data class GameEntity(
    @PrimaryKey val id: Long,
    val gameSectionId: Long,
    val week: String?,
    val tv: String?,
    val radio: String?,
    val wlt: String?,
    val gameState: String?,
    val awayScore: String?,
    val homeScore: String?,
    val type: String?,
    val dateText: String?,
    val dateTime: String?,
    val dateTimestamp: String?,
    @Embedded(prefix = "opponent_") val opponent: TeamEntity?
)

@Entity
data class TeamEntity(
    @PrimaryKey val triCode: String,
    val fullName: String?,
    val name: String?,
    val city: String?,
    val record: String?,
    val wins: Int?,
    val losses: Int?,
    val winPercentage: Double?,
    val primaryColor: String?
)