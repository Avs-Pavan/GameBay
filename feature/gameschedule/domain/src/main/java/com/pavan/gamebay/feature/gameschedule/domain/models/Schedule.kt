package com.pavan.gamebay.feature.gameschedule.domain.models


data class Schedule(
    val team: Team,
    val defaultGameId: Long,
    val gameSection: List<GameSection>,
)

data class GameSection(
    val heading: String,
    val game: List<Game>
)

data class Team(
    val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String,
    val wins: Int,
    val losses: Int,
    val winPercentage: Double,
    val primaryColor: String
)

data class Game(
    val id: Long,
    val week: String,
    val gameState: String,
    val tv: String,
    val radio: String,
    val gameOutcome: GameOutcome,
    val awayScore: Int,
    val homeScore: Int,
    val gameType: GameType,
    val gameDate: GameDate,
    val opponentTeam: Team
)

enum class GameOutcome {
    WIN,
    LOSS,
    TIE,
    NONE
}

data class GameDate(
    val dateText: String,
    val dateTime: String,
)

enum class GameType {
    FINAL,
    BYE,
    SCHEDULED,
    NONE
}