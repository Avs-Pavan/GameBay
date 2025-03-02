package com.pavan.gamebay.feature.gameschedule.presentation.screens

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.UIState
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUIModel


@Immutable
data class GameScheduleUIState(
    val defaultGameId: Long = 0L,
    val gameSections: UIState<List<GameSectionUIModel>> = UIState.Loading,
)


