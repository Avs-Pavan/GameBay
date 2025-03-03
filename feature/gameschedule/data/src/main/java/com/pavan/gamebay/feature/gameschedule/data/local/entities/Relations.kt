package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a schedule with its associated details.
 *
 * @property schedule The schedule entity.
 * @property gameSections The list of game sections with their associated games.
 */
data class ScheduleWithDetails(
    @Embedded val schedule: ScheduleEntity?,
    @Relation(
        entity = GameSectionEntity::class,
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val gameSections: List<GameSectionWithGames> = emptyList()
)

/**
 * Data class representing a game section with its associated games.
 *
 * @property gameSection The game section entity.
 * @property games The list of games in the game section.
 */
data class GameSectionWithGames(
    @Embedded val gameSection: GameSectionEntity?,
    @Relation(
        entity = GameEntity::class,
        parentColumn = "heading",
        entityColumn = "gameSectionHeading"
    )
    val games: List<GameEntity> = emptyList()
)