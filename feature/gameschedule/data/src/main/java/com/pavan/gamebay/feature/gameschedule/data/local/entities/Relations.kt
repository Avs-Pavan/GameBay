package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduleWithDetails(
    @Embedded val schedule: ScheduleEntity?,
    @Relation(
        entity = GameSectionEntity::class,
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val gameSections: List<GameSectionWithGames> = emptyList()
)

data class GameSectionWithGames(
    @Embedded val gameSection: GameSectionEntity?,
    @Relation(
        entity = GameEntity::class,
        parentColumn = "heading",
        entityColumn = "gameSectionHeading"
    )
    val games: List<GameEntity> = emptyList()
)