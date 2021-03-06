package com.mohdabbas.cityweather.data.source.local

import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData
import javax.inject.Inject

/**
 * Represent the the local data source and will be the entry point to interact with
 * the data in the database
 *
 * @author Mohammad Abbas
 * @property cityWeatherDao City weather Dao
 */
class LocalDataSource @Inject constructor(private val cityWeatherDao: CityWeatherDao) {

    /**
     * The function is used to store the data of the cities weather in the database
     *
     * @param citiesWeather List of cities weather data
     */
    suspend fun storeCitiesWeatherDataInDb(citiesWeather: List<CityWeatherData>) {
        cityWeatherDao.insertCitiesWeatherData(citiesWeather)
    }

    /**
     * The function is used to get the data of the cities weather from the database
     *
     * @return List of cities weather data
     */
    suspend fun getCitiesWeatherData(): List<CityWeatherData> {
        return cityWeatherDao.getCitiesWeatherData()
    }

    /**
     * The function is used to get the data of the cities weather from the database
     * by city name
     *
     * @return List of cities weather data
     */
    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeatherData> {
        return cityWeatherDao.getCitiesWeatherByCityName(cityName)
    }

    /**
     * This function is used to get the city weather details from the database
     * by city id
     *
     * @return City weather data
     */
    suspend fun getCityWeatherByCityId(cityId: Int): CityWeatherData {
        return cityWeatherDao.getCityWeatherByCityId(cityId)
    }
}