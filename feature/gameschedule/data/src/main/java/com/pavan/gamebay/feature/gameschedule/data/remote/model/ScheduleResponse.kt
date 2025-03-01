package com.pavan.gamebay.feature.gameschedule.data.remote.model


import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("Team")
    val team: TeamResponse,
    @SerializedName("DefaultGameId")
    val defaultGameId: Long,
    @SerializedName("GameSection")
    val gameSection: List<GameSectionResponse>,
    @SerializedName("Filters")
    val filters: List<FilterResponse>,
    @SerializedName("YinzNode")
    val yinzNode: YinzNodeResponse
)

data class TeamResponse(
    @SerializedName("TriCode")
    val triCode: String,
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("Record")
    val record: String,
    @SerializedName("Wins")
    val wins: String,
    @SerializedName("Losses")
    val losses: String,
    @SerializedName("WinPercentage")
    val winPercentage: String,
    @SerializedName("PrimaryColor")
    val primaryColor: String
)

data class GameSectionResponse(
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("Game")
    val game: List<GameResponse>
)

data class GameResponse(
    @SerializedName("Week")
    val week: String,
    @SerializedName("Label")
    val label: String,
    @SerializedName("TV")
    val tv: String?,
    @SerializedName("Radio")
    val radio: String?,
    @SerializedName("Venue")
    val venue: String?,
    @SerializedName("WLT")
    val wlt: String?,
    @SerializedName("GameState")
    val gameState: String,
    @SerializedName("Clock")
    val clock: String,
    @SerializedName("QuarterShorthand")
    val quarterShorthand: String,
    @SerializedName("Quarter")
    val quarter: String,
    @SerializedName("AwayScore")
    val awayScore: String?,
    @SerializedName("HomeScore")
    val homeScore: String?,
    @SerializedName("Down")
    val down: String?,
    @SerializedName("IsHome")
    val isHome: Boolean?,
    @SerializedName("ScheduleHeader")
    val scheduleHeader: String,
    @SerializedName("IsSuperStadium")
    val isSuperStadium: Boolean?,
    @SerializedName("CardData")
    val cardData: CardDataResponse,
    @SerializedName("Id")
    val id: Long,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Date")
    val date: GameDateResponse?,
    @SerializedName("Opponent")
    val opponent: OpponentResponse?,
    @SerializedName("Tickets")
    val tickets: TicketsResponse?,
    @SerializedName("Result")
    val result: String?,
    @SerializedName("Home")
    val home: Boolean?,
    @SerializedName("Buttons")
    val buttons: List<ButtonResponse>,
    @SerializedName("SecondaryButtons")
    val secondaryButtons: List<ButtonResponse>
)

data class CardDataResponse(
    @SerializedName("ClickthroughURL")
    val clickThroughURL: String,
    @SerializedName("IsDefault")
    val isDefault: String // "True" or "False" as string
)

data class GameDateResponse(
    @SerializedName("Numeric")
    val numeric: String,
    @SerializedName("Text")
    val text: String,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("IsTBA")
    val isTBA: String,
    @SerializedName("IsTba")
    val isTba: String
)

data class OpponentResponse(
    @SerializedName("TriCode")
    val triCode: String,
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("City")
    val city: String,
    @SerializedName("Record")
    val record: String
)

data class TicketsResponse(
    @SerializedName("HasLink")
    val hasLink: Boolean,
    @SerializedName("Label")
    val label: String,
    @SerializedName("Link")
    val link: String
)

data class ButtonResponse(
    @SerializedName("Title")
    val title: String,
    @SerializedName("URL")
    val url: String
)

data class FilterResponse(
    @SerializedName("Name")
    val name: String,
    @SerializedName("QueryParameter")
    val queryParameter: String,
    @SerializedName("Placeholder")
    val placeholder: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Current")
    val current: String,
    @SerializedName("Items")
    val items: List<FilterItemResponse>
)

data class FilterItemResponse(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
)

data class YinzNodeResponse(
    @SerializedName("InVenue")
    val inVenue: Boolean,
    @SerializedName("VenueStatus")
    val venueStatus: String,
    @SerializedName("Carrier")
    val carrier: String,
    @SerializedName("Ads")
    val ads: String,
    @SerializedName("Generated")
    val generated: String
)