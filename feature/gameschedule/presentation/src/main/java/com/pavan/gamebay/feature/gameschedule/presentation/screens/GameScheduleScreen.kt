package com.pavan.gamebay.feature.gameschedule.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun GameScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: GameScheduleViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Text(text = "Game Schedule Screen")

}