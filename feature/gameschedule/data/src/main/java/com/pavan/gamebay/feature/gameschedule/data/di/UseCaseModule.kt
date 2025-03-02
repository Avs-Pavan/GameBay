package com.pavan.gamebay.feature.gameschedule.data.di

import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import com.pavan.gamebay.feature.gameschedule.domain.usecase.GetGameScheduleUseCase
import com.pavan.gamebay.feature.gameschedule.domain.usecase.RefreshGameScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetGameScheduleUseCase(
        repository: IGameScheduleRepo
    ): GetGameScheduleUseCase {
        return GetGameScheduleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRefreshGameScheduleUseCase(
        repository: IGameScheduleRepo
    ): RefreshGameScheduleUseCase {
        return RefreshGameScheduleUseCase(repository)
    }
}