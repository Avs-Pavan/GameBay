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

@Module
@InstallIn(SingletonComponent::class)
abstract class GameScheduleBindModule {

    @Binds
    @Singleton
    abstract fun bindGameScheduleRepository(
        gameScheduleRepositoryImpl: GameScheduleRepo
    ): IGameScheduleRepo

    @Binds
    @Singleton
    abstract fun bindGameScheduleRoomMapper(
        gameScheduleRoomMapperImpl: GameScheduleRoomMapper
    ): IMapper<ScheduleWithDetails, Schedule>


    @Binds
    @Singleton
    abstract fun bindGameScheduleRemoteDataMapper(
        gameScheduleRemoteDataMapperImpl: GameScheduleRemoteDataMapper
    ): IMapper<ScheduleResponse, ScheduleWithDetails>

}