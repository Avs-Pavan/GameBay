package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response for a game section.
 *
 * @property heading The heading of the game section.
 * @property games The list of games in the game section.
 */
data class GameSectionResponse(
    @SerializedName("Heading")
    val heading: String? = null,
    @SerializedName("Game")
    val games: List<GameResponse>? = null
)