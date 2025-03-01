package com.pavan.gamebay.feature.gameschedule.domain.repo

import com.pavan.gamebay.core.domain.NetworkError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for fetching game schedules.
 */
fun interface IGameScheduleRepo {
    /**
     * Fetches the game schedule.
     * @return Result containing either the Schedule or a NetworkError.
     */
    fun getGameSchedule(): Flow<Result<Schedule, NetworkError>>
}