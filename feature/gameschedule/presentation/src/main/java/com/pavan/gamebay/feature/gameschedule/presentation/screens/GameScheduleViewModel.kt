package com.pavan.gamebay.feature.gameschedule.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavan.gamebay.core.domain.MainDispatcher
import com.pavan.gamebay.core.domain.onError
import com.pavan.gamebay.core.domain.onSuccess
import com.pavan.gamebay.core.presentaion.designsystem.ui.components.UIState
import com.pavan.gamebay.feature.gameschedule.domain.usecase.GetGameScheduleUseCase
import com.pavan.gamebay.feature.gameschedule.domain.usecase.RefreshGameScheduleUseCase
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameCardUIEvents
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameScheduleViewModel @Inject constructor(
    private val refreshGameSchedule: Lazy<RefreshGameScheduleUseCase>,
    private val getGameSchedule: Lazy<GetGameScheduleUseCase>,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameScheduleUIState())
    val uiState = _uiState.onStart {
        refreshGameSchedule.get().invoke()
        fetchGameSchedule()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GameScheduleUIState()
    )

    private fun fetchGameSchedule() {
        viewModelScope.launch {
            getGameSchedule.get().invoke().collectLatest { result ->
                result.onSuccess { schedule ->
                    withContext(mainDispatcher) {
                        _uiState.update { previousState ->
                            previousState.copy(
                                defaultGameId = schedule.defaultGameId,
                                gameSections = UIState.Ready(
                                    schedule.gameSection.map { gameSection ->
                                        gameSection.toUIModel(schedule.team)
                                    }
                                )
                            )
                        }
                    }
                }.onError {
                    Log.e(TAG, "Error: $it")
                }
            }
        }
    }

    fun onEvent(event: GameCardUIEvents) {
        when (event) {
            is GameCardUIEvents.OnGameClick -> {
                // Handle Game Click
                Log.d(TAG, "Game Clicked: ${event.gameUIModel}")
            }
        }
    }

    companion object {
        private const val TAG = "GameScheduleViewModel"
    }

}