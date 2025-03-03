package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a section of a game schedule.
 *
 * @property heading The heading of the game section.
 * @property scheduleId The ID of the schedule this section belongs to.
 */
@Entity
data class GameSectionEntity(
    @PrimaryKey
    val heading: String = "",
    val scheduleId: Long = 0,
)