package com.pavan.gamebay.feature.gameschedule.data.di

import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import com.pavan.gamebay.feature.gameschedule.domain.usecase.GetGameScheduleUseCase
import com.pavan.gamebay.feature.gameschedule.domain.usecase.RefreshGameScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides use cases for game schedule operations.
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    /**
     * Provides an instance of [GetGameScheduleUseCase].
     *
     * @param repository The repository to be used by the use case.
     * @return An instance of [GetGameScheduleUseCase].
     */
    @Provides
    @Singleton
    fun provideGetGameScheduleUseCase(
        repository: IGameScheduleRepo
    ): GetGameScheduleUseCase {
        return GetGameScheduleUseCase(repository)
    }

    /**
     * Provides an instance of [RefreshGameScheduleUseCase].
     *
     * @param repository The repository to be used by the use case.
     * @return An instance of [RefreshGameScheduleUseCase].
     */
    @Provides
    @Singleton
    fun provideRefreshGameScheduleUseCase(
        repository: IGameScheduleRepo
    ): RefreshGameScheduleUseCase {
        return RefreshGameScheduleUseCase(repository)
    }
}