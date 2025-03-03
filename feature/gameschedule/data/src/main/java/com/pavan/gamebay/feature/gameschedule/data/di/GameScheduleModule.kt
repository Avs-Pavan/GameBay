package com.pavan.gamebay.feature.gameschedule.data.di

import android.content.Context
import com.pavan.gamebay.feature.gameschedule.data.remote.api.GameScheduleAPI
import com.pavan.gamebay.feature.gameschedule.data.remote.datasource.GameScheduleRemoteDataSource
import com.pavan.gamebay.feature.gameschedule.data.remote.mapper.GameScheduleRemoteDataMapper
import com.pavan.gamebay.feature.gameschedule.data.room.GameScheduleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger module for providing dependencies related to the game schedule feature.
 */
@Module
@InstallIn(SingletonComponent::class)
class GameScheduleModule {

    /**
     * Provides the GameScheduleDatabase instance.
     *
     * @param context The application context.
     * @return The GameScheduleDatabase instance.
     */
    @Provides
    @Singleton
    fun provideGameScheduleDataBase(@ApplicationContext context: Context): GameScheduleDatabase {
        return GameScheduleDatabase.getDatabase(context)
    }

    /**
     * Provides the GameScheduleDao instance.
     *
     * @param gameScheduleDatabase The GameScheduleDatabase instance.
     * @return The GameScheduleDao instance.
     */
    @Provides
    @Singleton
    fun provideGameScheduleDao(gameScheduleDatabase: GameScheduleDatabase) =
        gameScheduleDatabase.scheduleDao()

    /**
     * Provides the GameScheduleAPI instance.
     *
     * @param retrofit The Retrofit instance.
     * @return The GameScheduleAPI instance.
     */
    @Provides
    @Singleton
    fun provideGameScheduleApi(retrofit: Retrofit) = retrofit.create(GameScheduleAPI::class.java)

    /**
     * Provides the GameScheduleRemoteDataSource instance.
     *
     * @param gameScheduleAPI The GameScheduleAPI instance.
     * @param mapper The GameScheduleRemoteDataMapper instance.
     * @return The GameScheduleRemoteDataSource instance.
     */
    @Provides
    @Singleton
    fun provideGameScheduleRemoteDataSource(
        gameScheduleAPI: GameScheduleAPI,
        mapper: GameScheduleRemoteDataMapper
    ) = GameScheduleRemoteDataSource(gameScheduleAPI, mapper)
}