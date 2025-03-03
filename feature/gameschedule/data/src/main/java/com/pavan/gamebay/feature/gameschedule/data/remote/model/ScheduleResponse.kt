package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName


/**
 * Data class representing the response for a game schedule.
 *
 * @property defaultGameId The ID of the default game.
 * @property team The team information.
 * @property gameSections The list of game sections.
 */
data class ScheduleResponse(
    @SerializedName("DefaultGameId")
    val defaultGameId: String? = null,
    @SerializedName("Team")
    val team: TeamResponse? = null,
    @SerializedName("GameSection")
    val gameSections: List<GameSectionResponse>? = null
)