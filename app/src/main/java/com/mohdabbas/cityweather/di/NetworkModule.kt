package com.mohdabbas.cityweather.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * The network module that will provide the [OkHttpClient], A Singleton module
 *
 * @author Mohammad Abbas
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * This function provide a Singleton instance of [OkHttpClient] dependency.
     *
     * @return [OkHttpClient] the okHttpClient.
     */
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}