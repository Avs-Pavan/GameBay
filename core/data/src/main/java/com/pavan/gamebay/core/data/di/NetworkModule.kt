package com.pavan.gamebay.core.data.di

import com.google.gson.Gson
import com.pavan.gamebay.core.data.BuildConfig
import com.pavan.gamebay.core.domain.CoreDomainConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
     * Provides an [okhttp3.OkHttpClient] for handling network requests.
     *
     * @param loggingInterceptor The logging interceptor to use.
     * @return An instance of [okhttp3.OkHttpClient].
     */
    @Singleton
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
            }
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
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