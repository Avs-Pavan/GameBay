package com.pavan.gamebay.feature.gameschedule.domain.repo

import com.pavan.gamebay.core.domain.MError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for fetching game schedules.
 */
interface IGameScheduleRepo {
    /**
     * Fetches the game schedule.
     * @return Result containing either the Schedule or a NetworkError.
     */
    fun getGameSchedule(): Flow<Result<Schedule, MError>>

    /**
     * Refreshes the game schedule.
     */
    suspend fun refreshGameSchedule()
}