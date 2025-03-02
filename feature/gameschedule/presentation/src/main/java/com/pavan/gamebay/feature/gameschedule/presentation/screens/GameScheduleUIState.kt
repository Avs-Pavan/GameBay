package com.pavan.gamebay.feature.gameschedule.presentation.screens

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.UIState
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType


@Immutable
data class GameScheduleUIState(
    val homeTeam: UIState<TeamUIModel> = UIState.Loading,
    val defaultGameId: Long = 0L,
    val gameSection: UIState<List<GameSectionUI>> = UIState.Loading,
)


@Immutable
data class GameSectionUI(
    val heading: String,
    val game: List<GameUIModel>,
)

@Immutable
data class TeamUIModel(
    val triCode: String,
    val name: String,
    val record: String,
    val wins: Int,
    val losses: Int,
    val teamLogoUrl: String,
)

@Immutable
data class GameUIModel(
    val week: String,
    val gameState: String,
    val tv: String,
    val radio: String,
    val gameOutcome: GameOutcome,
    val awayScore: Int,
    val homeScore: Int,
    val gameType: GameType,
    val gameDate: GameDate,
    val opponentTeam: TeamUIModel,
)

