package com.pavan.gamebay.feature.gameschedule.data.remote.datasource

import com.pavan.gamebay.core.data.mappedSafeCall
import com.pavan.gamebay.core.domain.MError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.remote.api.GameScheduleAPI
import com.pavan.gamebay.feature.gameschedule.data.remote.mapper.GameScheduleRemoteDataMapper
import javax.inject.Inject

/**
 * Data source for fetching game schedules from a remote API.
 *
 * @property scheduleAPI The API interface for fetching game schedules.
 * @property mapper The mapper for converting API responses to domain models.
 */
class GameScheduleRemoteDataSource @Inject constructor(
    private val scheduleAPI: GameScheduleAPI,
    private val mapper: GameScheduleRemoteDataMapper
) {
    suspend fun getGameSchedule(): Result<ScheduleWithDetails, MError> {
        return mappedSafeCall(mapper) {
            scheduleAPI.getGameSchedule()
        }
    }
}