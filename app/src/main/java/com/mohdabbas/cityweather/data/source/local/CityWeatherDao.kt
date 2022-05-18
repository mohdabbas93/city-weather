package com.mohdabbas.cityweather.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData

/**
 * Represent the city weather Dao that contain functions to interact
 * with data in the [CityWeatherData] table
 *
 * @author Mohammad Abbas
 */
@Dao
interface CityWeatherDao {

    /**
     *  The function used to insert/update cities weather
     *
     *  @param citiesWeather Cities weather to be inserted/updated
     */
    @Insert
    suspend fun insertCitiesWeatherData(citiesWeather: List<CityWeatherData>)

    /**
     *  The function used to retrieve a list of cities weather from
     *  cities_weather table
     *
     *  @return List of cities weather
     */
    @Query("SELECT * FROM city_weather")
    suspend fun getCitiesWeatherData(): List<CityWeatherData>

    /**
     *  The function used to filter cities weather for cities_weather table based on the
     *  city name
     *
     *  @return List of cities weather
     */
    @Query("SELECT * FROM city_weather WHERE city_name LIKE '%' || :cityName || '%'")
    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeatherData>
}