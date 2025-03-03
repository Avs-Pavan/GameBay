package com.pavan.gamebay.feature.gameschedule.presentation.screens

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.UIState
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUIModel


/**
 * Represents the UI state for the game schedule screen.
 *
 * @property defaultGameId The ID of the default game.
 * @property gameSections The state of the game sections, which is a list of `GameSectionUIModel`.
 */
@Immutable
data class GameScheduleUIState(
    val defaultGameId: Long = 0L,
    val gameSections: UIState<List<GameSectionUIModel>> = UIState.Loading,
)