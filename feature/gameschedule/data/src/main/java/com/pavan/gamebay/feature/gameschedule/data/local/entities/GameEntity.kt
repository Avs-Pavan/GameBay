package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val scheduleId: Long = 0,
    @Embedded val team: TeamEntity,
    val defaultGameId: Long,
    @Embedded val yinzNode: YinzNodeEntity
)

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Long,
    val type: String, // "F" (Final), "B" (Bye), "S" (Scheduled)
    val week: String,
    val label: String,
    val gameState: String,
    val clock: String,
    val quarterShorthand: String,
    val quarter: String,
    val scheduleHeader: String,
    @Embedded val cardData: CardDataEntity,
    val tv: String? = null,
    val radio: String? = null,
    val venue: String? = null,
    val wlt: String? = null, // Win/Loss/Tie
    val awayScore: String? = null,
    val homeScore: String? = null,
    val down: String? = null,
    val isHome: Boolean? = null,
    val isSuperStadium: Boolean? = null,
    @Embedded val date: GameDateEntity? = null,
    @Embedded(prefix = "opponent_") val opponent: OpponentEntity? = null,
    @Embedded val tickets: TicketsEntity? = null,
    val result: String? = null,
    val home: Boolean? = null,
    val sectionHeading: String // Links to GameSection
)

@Entity(tableName = "game_sections")
data class GameSectionEntity(
    @PrimaryKey val heading: String,
    val scheduleId: Long // Links to ScheduleEntity
)

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String,
    val wins: String,
    val losses: String,
    val winPercentage: String,
    val primaryColor: String
)

@Entity(tableName = "card_data")
data class CardDataEntity(
    @PrimaryKey(autoGenerate = true)
    val cardId: Long = 0,
    val clickThroughURL: String,
    val isDefault: Boolean
)

@Entity(tableName = "game_dates")
data class GameDateEntity(
    @PrimaryKey(autoGenerate = true)
    val dateId: Long = 0,
    val numeric: String,
    val text: String,
    val time: String,
    val timestamp: String,
    val isTBA: Boolean,
    val isTba: Boolean
)

@Entity(tableName = "opponents")
data class OpponentEntity(
    @PrimaryKey val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String
)

@Entity(tableName = "tickets")
data class TicketsEntity(
    @PrimaryKey(autoGenerate = true)
    val ticketId: Long = 0,
    val hasLink: Boolean,
    val label: String,
    val link: String
)

@Entity(
    tableName = "buttons",
    foreignKeys = [ForeignKey(
        entity = GameEntity::class,
        parentColumns = ["id"],
        childColumns = ["gameId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ButtonEntity(
    @PrimaryKey(autoGenerate = true)
    val buttonId: Long = 0,
    val title: String,
    val url: String,
    val gameId: Long,
    val isSecondary: Boolean
)

@Entity(tableName = "filters")
data class FilterEntity(
    @PrimaryKey val queryParameter: String,
    val name: String,
    val placeholder: String,
    val type: String,
    val current: String,
    val scheduleId: Long // Links to ScheduleEntity
)

@Entity(
    tableName = "filter_items",
    foreignKeys = [ForeignKey(
        entity = FilterEntity::class,
        parentColumns = ["queryParameter"],
        childColumns = ["filterQueryParameter"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FilterItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val filterQueryParameter: String
)

@Entity(tableName = "yinz_nodes")
data class YinzNodeEntity(
    @PrimaryKey(autoGenerate = true)
    val nodeId: Long = 0,
    val inVenue: Boolean,
    val venueStatus: String,
    val carrier: String,
    val ads: String,
    val generated: String
)