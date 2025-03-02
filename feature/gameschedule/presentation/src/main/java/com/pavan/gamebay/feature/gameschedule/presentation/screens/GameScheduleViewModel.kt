package com.pavan.gamebay.feature.gameschedule.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavan.gamebay.core.domain.onError
import com.pavan.gamebay.core.domain.onSuccess
import com.pavan.gamebay.feature.gameschedule.domain.usecase.GetGameScheduleUseCase
import com.pavan.gamebay.feature.gameschedule.domain.usecase.RefreshGameScheduleUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScheduleViewModel @Inject constructor(
    private val refreshGameSchedule: Lazy<RefreshGameScheduleUseCase>,
    private val getGameSchedule: Lazy<GetGameScheduleUseCase>
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
            getGameSchedule.get().invoke().collect { result ->
                result.onSuccess { schedule ->
                    Log.d("GameScheduleViewModel", "Game Schedule: $schedule")
                }.onError {
                    Log.e("GameScheduleViewModel", "Error: $it")
                }
            }
        }
    }

}