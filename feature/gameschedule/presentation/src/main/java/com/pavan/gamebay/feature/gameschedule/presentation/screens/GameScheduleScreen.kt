package com.pavan.gamebay.feature.gameschedule.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.HandleState
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.LottieUI
import com.pavan.gamebay.core.presentaion.designsystem.ui.theme.GameBayTheme
import com.pavan.gamebay.feature.gameschedule.presentation.R
import com.pavan.gamebay.feature.gameschedule.presentation.screens.PreviewConstants.SAMPLE_GAME_SCHEDULE_UI_STATE
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameCardUIEvents
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUI


/**
 * Composable function for displaying the game schedule screen.
 *
 * @param modifier Modifier for styling the composable.
 * @param viewModel ViewModel for managing the UI state.
 */
@Composable
fun GameScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: GameScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScheduleScreenContent(
        modifier = modifier,
        uiState = uiState
    ) { gameCardUIEvents ->
        viewModel.onEvent(gameCardUIEvents)
    }
}

/**
 * Composable function for displaying the content of the game schedule screen.
 *
 * @param modifier Modifier for styling the composable.
 * @param uiState The UI state of the game schedule.
 * @param onEvent Callback for handling game card UI events.
 */
@Composable
private fun GameScheduleScreenContent(
    modifier: Modifier = Modifier,
    uiState: GameScheduleUIState,
    onEvent: (GameCardUIEvents) -> Unit = {}
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            uiState.gameSections.HandleState(
                onLoading = {
                    LottieUI(
                        modifier = Modifier.fillMaxSize(),
                        jsonRes = R.raw.shimmer
                    )
                },
                onError = {
                    // Error State
                },
                onReady = { gameSections ->
                    gameSections.forEach { gameSectionUIModel ->
                        GameSectionUI(
                            gameSectionUIModel = gameSectionUIModel
                        ) { gameCardUIEvents ->
                            // Handle GameCardUIEvents
                            onEvent(gameCardUIEvents)
                        }
                    }
                }
            )
        }
    }
}

/**
 * Preview function for the GameScheduleScreenContent composable.
 */
@Preview
@Composable
private fun Preview() {
    GameBayTheme {
        GameScheduleScreenContent(
            uiState = SAMPLE_GAME_SCHEDULE_UI_STATE
        )
    }
}