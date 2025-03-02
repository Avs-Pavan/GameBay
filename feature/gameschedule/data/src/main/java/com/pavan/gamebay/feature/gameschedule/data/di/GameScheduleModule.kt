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

@Module
@InstallIn(SingletonComponent::class)
class GameScheduleModule {

    @Provides
    @Singleton
    fun provideGameScheduleDataBase(@ApplicationContext context: Context): GameScheduleDatabase {
        return GameScheduleDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideGameScheduleDao(gameScheduleDatabase: GameScheduleDatabase) =
        gameScheduleDatabase.scheduleDao()


    @Provides
    @Singleton
    fun provideGameScheduleApi(retrofit: Retrofit) = retrofit.create(GameScheduleAPI::class.java)

    @Provides
    @Singleton
    fun provideGameScheduleRemoteDataSource(
        gameScheduleAPI: GameScheduleAPI,
        mapper: GameScheduleRemoteDataMapper
    ) = GameScheduleRemoteDataSource(gameScheduleAPI, mapper)


}