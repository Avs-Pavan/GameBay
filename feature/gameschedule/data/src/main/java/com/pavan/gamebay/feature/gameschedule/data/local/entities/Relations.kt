package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation


// Data class to hold the full schedule with relationships
data class ScheduleWithRelations(
    @Embedded val schedule: ScheduleEntity,
    @Relation(
        parentColumn = "scheduleId",
        entityColumn = "scheduleId",
        entity = GameSectionEntity::class
    )
    val gameSections: List<GameSectionWithGames>,
    @Relation(
        parentColumn = "scheduleId",
        entityColumn = "scheduleId",
        entity = FilterEntity::class
    )
    val filters: List<FilterWithItems>
)

data class GameSectionWithGames(
    @Embedded val section: GameSectionEntity,
    @Relation(
        parentColumn = "heading",
        entityColumn = "sectionHeading",
        entity = GameEntity::class
    )
    val games: List<GameWithButtons>
)

data class GameWithButtons(
    @Embedded val game: GameEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId",
        entity = ButtonEntity::class
    )
    val buttons: List<ButtonEntity>
)


data class FilterWithItems(
    @Embedded val filter: FilterEntity,
    @Relation(
        parentColumn = "queryParameter",
        entityColumn = "filterQueryParameter",
        entity = FilterItemEntity::class
    )
    val items: List<FilterItemEntity>
)
