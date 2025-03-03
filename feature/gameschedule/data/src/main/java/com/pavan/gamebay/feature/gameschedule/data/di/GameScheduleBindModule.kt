package com.pavan.gamebay.feature.gameschedule.data.di

import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.local.mapper.GameScheduleRoomMapper
import com.pavan.gamebay.feature.gameschedule.data.remote.mapper.GameScheduleRemoteDataMapper
import com.pavan.gamebay.feature.gameschedule.data.remote.model.ScheduleResponse
import com.pavan.gamebay.feature.gameschedule.data.repo.GameScheduleRepo
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for binding game schedule related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class GameScheduleBindModule {

    /**
     * Binds the implementation of [GameScheduleRepo] to the [IGameScheduleRepo] interface.
     *
     * @param gameScheduleRepositoryImpl The implementation of the game schedule repository.
     * @return The bound game schedule repository.
     */
    @Binds
    @Singleton
    abstract fun bindGameScheduleRepository(
        gameScheduleRepositoryImpl: GameScheduleRepo
    ): IGameScheduleRepo

    /**
     * Binds the implementation of [GameScheduleRoomMapper] to the [IMapper] interface for
     * mapping [ScheduleWithDetails] to [Schedule].
     *
     * @param gameScheduleRoomMapperImpl The implementation of the game schedule room mapper.
     * @return The bound game schedule room mapper.
     */
    @Binds
    @Singleton
    abstract fun bindGameScheduleRoomMapper(
        gameScheduleRoomMapperImpl: GameScheduleRoomMapper
    ): IMapper<ScheduleWithDetails, Schedule>

    /**
     * Binds the implementation of [GameScheduleRemoteDataMapper] to the [IMapper] interface for
     * mapping [ScheduleResponse] to [ScheduleWithDetails].
     *
     * @param gameScheduleRemoteDataMapperImpl The implementation of the game schedule remote data mapper.
     * @return The bound game schedule remote data mapper.
     */
    @Binds
    @Singleton
    abstract fun bindGameScheduleRemoteDataMapper(
        gameScheduleRemoteDataMapperImpl: GameScheduleRemoteDataMapper
    ): IMapper<ScheduleResponse, ScheduleWithDetails>

}