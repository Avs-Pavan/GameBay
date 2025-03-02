package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType

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

