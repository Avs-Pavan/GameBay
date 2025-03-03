package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType

/**
 * Immutable data class representing the UI model for a game.
 *
 * @property week The week of the game.
 * @property gameState The current state of the game.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property gameOutcome The outcome of the game.
 * @property awayScore The score of the away team.
 * @property homeScore The score of the home team.
 * @property gameType The type of the game.
 * @property gameDate The date of the game.
 * @property opponentTeam The UI model of the opponent team.
 * @property homeTeam The UI model of the home team.
 */
@Immutable
data class GameUIModel(
    val week: String,
    val gameState: String,
    val tv: String,
    val radio: String,
    val gameOutcome: GameOutcome,
    val awayScore: String,
    val homeScore: String,
    val gameType: GameType,
    val gameDate: GameDate,
    val opponentTeam: TeamUIModel,
    val homeTeam: TeamUIModel
)