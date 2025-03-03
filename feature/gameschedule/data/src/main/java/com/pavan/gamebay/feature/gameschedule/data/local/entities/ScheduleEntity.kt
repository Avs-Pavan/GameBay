package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a schedule in the database.
 *
 * @property id The unique identifier for the schedule.
 * @property defaultGameId The default game ID associated with the schedule.
 * @property team The embedded team entity associated with the schedule.
 */
@Entity
data class ScheduleEntity(
    @PrimaryKey
    val id: Long = 0L,
    val defaultGameId: String = "",
    @Embedded val team: TeamEntity = TeamEntity(),
)