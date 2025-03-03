package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a game.
 *
 * @property week The week of the game.
 * @property label The label of the game.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property wlt The win-loss-tie record.
 * @property gameState The current state of the game.
 * @property awayScore The score of the away team.
 * @property homeScore The score of the home team.
 * @property id The unique identifier of the game.
 * @property type The type of the game.
 * @property date The date of the game.
 * @property opponent The opponent team information.
 */
data class GameResponse(
    @SerializedName("Week")
    val week: String? = null,
    @SerializedName("Label")
    val label: String? = null,
    @SerializedName("TV")
    val tv: String? = null,
    @SerializedName("Radio")
    val radio: String? = null,
    @SerializedName("WLT")
    val wlt: String? = null,
    @SerializedName("GameState")
    val gameState: String? = null,
    @SerializedName("AwayScore")
    val awayScore: String? = null,
    @SerializedName("HomeScore")
    val homeScore: String? = null,
    @SerializedName("Id")
    val id: Long? = null,
    @SerializedName("Type")
    val type: String? = null,
    @SerializedName("Date")
    val date: MDateResponse? = null,
    @SerializedName("Opponent")
    val opponent: TeamResponse? = null,
)