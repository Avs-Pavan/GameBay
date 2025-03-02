package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduleWithDetails(
    @Embedded val schedule: ScheduleEntity,
    @Relation(
        entity = GameSectionEntity::class,
        parentColumn = "defaultGameId",
        entityColumn = "scheduleId"
    )
    val gameSections: List<GameSectionWithGames>
)

data class GameSectionWithGames(
    @Embedded val gameSection: GameSectionEntity,
    @Relation(
        entity = GameEntity::class,
        parentColumn = "id",
        entityColumn = "gameSectionId"
    )
    val games: List<GameWithOpponent>
)

data class GameWithOpponent(
    @Embedded val game: GameEntity,
    @Embedded(prefix = "opponent_") val opponent: TeamEntity?
)