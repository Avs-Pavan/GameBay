package com.pavan.gamebay.feature.gameschedule.data.repo

import android.util.Log
import com.pavan.gamebay.core.domain.DataError
import com.pavan.gamebay.core.domain.IODispatcher
import com.pavan.gamebay.core.domain.MError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.core.domain.onError
import com.pavan.gamebay.core.domain.onSuccess
import com.pavan.gamebay.feature.gameschedule.data.local.dao.GameScheduleDao
import com.pavan.gamebay.feature.gameschedule.data.local.mapper.GameScheduleRoomMapper
import com.pavan.gamebay.feature.gameschedule.data.remote.datasource.GameScheduleRemoteDataSource
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameScheduleRepo @Inject constructor(
    private val remoteDataSource: GameScheduleRemoteDataSource,
    private val localDataSource: GameScheduleDao,
    private val gameScheduleRoomMapper: GameScheduleRoomMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : IGameScheduleRepo {

    override fun getGameSchedule(): Flow<Result<Schedule, MError>> {
        return localDataSource.getScheduleWithDetails().map { cachedSchedule ->
            cachedSchedule?.let {
                Result.Success(gameScheduleRoomMapper.map(cachedSchedule))
            } ?: Result.Error(DataError("No data found"))
        }
    }

    override suspend fun refreshGameSchedule() {
        remoteDataSource.getGameSchedule().onSuccess { schedule ->
            withContext(ioDispatcher) {
                // Insert schedule
                schedule.schedule?.let {
                    localDataSource.insertSchedule(it)
                }


                // Insert game sections
                schedule.gameSections.forEach { gameSection ->
                    gameSection.gameSection?.let {
                        localDataSource.insertGameSection(it)
                        // Insert games
                        val games = gameSection.games.map {
                            it.copy(gameSectionHeading = gameSection.gameSection.heading)
                        }
                        localDataSource.insertGames(games)
                    }
                }

            }
        }.onError {
            Log.e(TAG, "Error refreshing game schedule: $it")
        }
    }

    companion object {
        const val TAG = "GameScheduleRepo"
    }
}