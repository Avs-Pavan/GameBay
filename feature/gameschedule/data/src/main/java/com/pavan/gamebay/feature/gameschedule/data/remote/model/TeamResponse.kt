package com.pavan.gamebay.feature.gameschedule.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a response for a team.
 *
 * @property triCode The team's tri-code.
 * @property fullName The full name of the team.
 * @property name The name of the team.
 * @property city The city the team is based in.
 * @property record The team's record.
 * @property wins The number of wins the team has.
 * @property losses The number of losses the team has.
 * @property winPercentage The win percentage of the team.
 * @property primaryColor The primary color of the team.
 */
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