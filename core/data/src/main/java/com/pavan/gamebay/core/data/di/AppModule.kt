package com.pavan.gamebay.core.data.di

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IODispatcher
import com.pavan.gamebay.core.domain.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * App module for providing app-level dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides the IO dispatcher for background tasks.
     * @return [CoroutineDispatcher] configured for IO operations.
     */
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides the Main dispatcher for UI-related tasks.
     * @return [CoroutineDispatcher] configured for the main thread.
     */
    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    /**
     * Provides the Default dispatcher for CPU-intensive tasks.
     * @return [CoroutineDispatcher] configured for CPU-intensive operations.
     */
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

}