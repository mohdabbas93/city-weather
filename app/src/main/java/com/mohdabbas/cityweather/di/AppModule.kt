package com.mohdabbas.cityweather.di

import android.content.Context
import androidx.room.Room
import com.mohdabbas.cityweather.data.source.local.CityWeatherDao
import com.mohdabbas.cityweather.data.source.local.CityWeatherDatabase
import com.mohdabbas.cityweather.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * The app module that will provide the [CityWeatherDao]
 * [CityWeatherDatabase], and [ApiService], A Singleton module
 *
 * @author Mohammad Abbas
 */

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * This function provide the [CityWeatherDao] dependency.
     *
     * @param cityWeatherDatabase the app database.
     * @return [CityWeatherDao]
     */
    @Provides
    fun provideCityWeatherDao(cityWeatherDatabase: CityWeatherDatabase): CityWeatherDao {
        return cityWeatherDatabase.cityWeatherDao()
    }

    /**
     *  This function provide a Singleton [CityWeatherDatabase] dependency.
     *
     *  @param appContext The application context
     *  @return An instance of the [CityWeatherDatabase]
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CityWeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            CityWeatherDatabase::class.java,
            "city-weather-db"
        ).build()
    }

    /**
     *  This function provide a Singleton [ApiService] dependency.
     *
     *  @param client The [OkHttpClient] instance
     *  @return [ApiService]
     */
    @Provides
    @Singleton
    fun provideAPIService(
        client: OkHttpClient,
    ): ApiService = createRetrofit(client).create(ApiService::class.java)

    private fun createRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("http://bulk.openweathermap.org/")
            .client(client)
            .build()
}