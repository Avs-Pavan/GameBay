package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName


data class ScheduleResponse(
    @SerializedName("DefaultGameId")
    val defaultGameId: String? = null,
    @SerializedName("Team")
    val team: TeamResponse? = null,
    @SerializedName("GameSection")
    val gameSections: List<GameSectionResponse>? = null
)

data class GameSectionResponse(
    @SerializedName("Heading")
    val heading: String? = null,
    @SerializedName("Game")
    val games: List<GameResponse>? = null
)

data class GameResponse(
    @SerializedName("Week")
    val week: String? = null,
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

data class TeamResponse(
    @SerializedName("TriCode")
    val triCode: String? = null,
    @SerializedName("FullName")
    val fullName: String? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("City")
    val city: String? = null,
    @SerializedName("Record")
    val record: String? = null,
    @SerializedName("Wins")
    val wins: Int? = null,
    @SerializedName("Losses")
    val losses: Int? = null,
    @SerializedName("WinPercentage")
    val winPercentage: Double? = null,
    @SerializedName("PrimaryColor")
    val primaryColor: String? = null,
)


data class MDateResponse(
    @SerializedName("Numeric")
    val numeric: String? = null,
    @SerializedName("Text")
    val text: String? = null,
    @SerializedName("Time")
    val time: String? = null,
    @SerializedName("Timestamp")
    val timestamp: String? = null,
    @SerializedName("IsTBA")
    val isTBA: String? = null,
)