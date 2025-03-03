package com.pavan.gamebay.feature.gameschedule.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity class representing a game in the local database.
 */
@Entity
data class GameEntity(
    /**
     * Primary key for the game entity.
     */
    @PrimaryKey val id: Long = 0,

    /**
     * Heading for the game section.
     */
    val gameSectionHeading: String = "",

    /**
     * Week of the game.
     */
    val week: String = "",

    /**
     * Label for the game.
     */
    val label: String = "",

    /**
     * TV channel broadcasting the game.
     */
    val tv: String = "",

    /**
     * Radio station broadcasting the game.
     */
    val radio: String = "",

    /**
     * Win-Loss-Tie record.
     */
    val wlt: String = "",

    /**
     * Current state of the game.
     */
    val gameState: String = "",

    /**
     * Score of the away team.
     */
    val awayScore: String = "",

    /**
     * Score of the home team.
     */
    val homeScore: String = "",

    /**
     * Type of the game.
     */
    val type: String = "",

    /**
     * Text representation of the game date.
     */
    val dateText: String = "",

    /**
     * Date and time of the game.
     */
    val dateTime: String = "",

    /**
     * Timestamp of the game date.
     */
    val dateTimestamp: String = "",

    /**
     * Embedded opponent team entity with a prefix.
     */
    @Embedded(prefix = "opponent_") val opponent: TeamEntity = TeamEntity(),
)