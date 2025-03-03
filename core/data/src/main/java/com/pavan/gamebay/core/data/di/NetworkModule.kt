package com.pavan.gamebay.core.data.di

import android.content.Context
import com.google.gson.Gson
import com.pavan.gamebay.core.data.BuildConfig
import com.pavan.gamebay.core.domain.CoreDomainConstants
import com.pavan.rapidqa.interceptors.delay.RapidQADelayInterceptor
import com.pavan.rapidqa.interceptors.tag.RapidQANameTagInterceptor
import com.pavan.rapidqa.mocker.RapidQAMockInterceptor
import com.pavan.rapidqa.store.RapidQADataStore
import com.pavan.rapidqa.store.RapidQAInMemoryDataStore
import com.pavan.rapidqa.tracer.RapidQATraceRecord
import com.pavan.rapidqa.tracer.RapidQaTracer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Hilt module for providing network related dependencies.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a [com.google.gson.Gson] instance for JSON serialization and deserialization.
     *
     * @return An instance of [com.google.gson.Gson].
     * */
    @Provides
    @Singleton
    fun provideGson() = Gson()


    /**
     * Provides a [retrofit2.converter.gson.GsonConverterFactory] for converting JSON data.
     *
     * @return An instance of [retrofit2.converter.gson.GsonConverterFactory].
     */
    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    /**
     * Provides a logging interceptor for HTTP requests.
     *
     * @return An instance of [okhttp3.logging.HttpLoggingInterceptor].
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    /**
     * Provides a [RapidQAMockInterceptor] for mocking network responses.
     *
     * @param context The application context.
     * @return An instance of [RapidQAMockInterceptor].
     */
    @Singleton
    @Provides
    fun provideMockInterceptor(
        @ApplicationContext context: Context
    ): RapidQAMockInterceptor {
        return RapidQAMockInterceptor(context.assets, isGlobalMockingEnabled = { true })
    }

    /**
     * Provides a [RapidQADataStore] for storing trace records.
     *
     * @return An instance of [RapidQADataStore].
     */
    @Singleton
    @Provides
    fun provideQADataStore(): RapidQADataStore<Long, RapidQATraceRecord> {
        return RapidQAInMemoryDataStore()
    }

    /**
     * Provides a [RapidQaTracer] for tracing network requests.
     *
     * @param dataStore The data store for trace records.
     * @return An instance of [RapidQaTracer].
     */
    @Singleton
    @Provides
    fun provideQATracer(dataStore: RapidQADataStore<Long, RapidQATraceRecord>): RapidQaTracer {
        return RapidQaTracer(dataStore)
    }

    /**
     * Provides a [RapidQADelayInterceptor] for adding delays to network requests.
     *
     * @return An instance of [RapidQADelayInterceptor].
     */
    @Singleton
    @Provides
    fun provideDelayedInterceptor(): RapidQADelayInterceptor {
        return RapidQADelayInterceptor(isDelayEnabled = { true })
    }

    /**
     * Provides a [RapidQANameTagInterceptor] for tagging network requests.
     *
     * @return An instance of [RapidQANameTagInterceptor].
     */
    @Singleton
    @Provides
    fun provideNameTagInterceptor(): RapidQANameTagInterceptor {
        return RapidQANameTagInterceptor()
    }

    /**
     * Provides an [OkHttpClient] configured with various interceptors.
     *
     * @param loggingInterceptor The logging interceptor.
     * @param rapidQAMockInterceptor The mock interceptor.
     * @param rapidQaTracer The tracer interceptor.
     * @param rapidQADelayInterceptor The delay interceptor.
     * @param rapidQaNameTagInterceptor The name tag interceptor.
     * @return An instance of [OkHttpClient].
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        rapidQAMockInterceptor: RapidQAMockInterceptor,
        rapidQaTracer: RapidQaTracer,
        rapidQADelayInterceptor: RapidQADelayInterceptor,
        rapidQaNameTagInterceptor: RapidQANameTagInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(rapidQaNameTagInterceptor)
            .addInterceptor(rapidQADelayInterceptor)
            .addInterceptor(rapidQaTracer)
            .addInterceptor(rapidQAMockInterceptor)
        return builder.build()
    }


    /**
     * Provides a [retrofit2.Retrofit] instance for making network requests.
     *
     * @param okHttpClient The [OkHttpClient] to use.
     * @param gsonConverterFactory The [GsonConverterFactory] to use.
     * @return An instance of [retrofit2.Retrofit].
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CoreDomainConstants.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


}