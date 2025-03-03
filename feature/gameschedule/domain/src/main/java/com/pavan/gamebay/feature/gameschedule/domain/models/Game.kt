package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a Game.
 *
 * @property id The unique identifier of the game.
 * @property week The week of the game.
 * @property label The label of the game.
 * @property gameState The current state of the game.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property gameOutcome The outcome of the game.
 * @property awayScore The score of the away team.
 * @property homeScore The score of the home team.
 * @property gameType The type of the game.
 * @property gameDate The date of the game.
 * @property opponentTeam The opponent team.
 */
data class Game(
    val id: Long,
    val week: String,
    val label: String,
    val gameState: String,
    val tv: String,
    val radio: String,
    val gameOutcome: GameOutcome,
    val awayScore: String,
    val homeScore: String,
    val gameType: GameType,
    val gameDate: GameDate,
    val opponentTeam: Team
)