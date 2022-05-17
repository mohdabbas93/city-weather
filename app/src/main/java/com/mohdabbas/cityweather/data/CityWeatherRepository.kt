package com.mohdabbas.cityweather.data

import com.mohdabbas.cityweather.data.source.local.LocalDataSource
import com.mohdabbas.cityweather.util.JsonParser
import javax.inject.Inject

/**
 * The repository to interact with the cities weather data
 *
 * @author Mohammad Abbas
 * @property localDataSource an instance of the [LocalDataSource] class
 * @property jsonParser an instance of the [JsonParser] class.
 * @property mapper an instance of the [Mapper] class
 */
class CityWeatherRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val jsonParser: JsonParser,
    private val mapper: Mapper
) {

    /**
     *  Used to get the list of [CityWeather] from the database.
     *
     *  If the result from the database is empty the data will be parsed
     *  from the Json file in the assets folder and after that will be
     *  stored in the database and get the list of [CityWeather] again.
     *
     * @return a list of [CityWeather].
     */
    suspend fun getCitiesWeather(): List<CityWeather> {
        val citiesWeather = localDataSource.getCitiesWeatherData()

        return if (citiesWeather.isEmpty()) {
            updateCityWeatherDataInDb()
            mapper.run { localDataSource.getCitiesWeatherData().toCityWeather() }
        } else {
            mapper.run { citiesWeather.toCityWeather() }
        }
    }

    /**
     * Used to parse the list of [CityWeather] data from the Json file in the
     * assets folder using [JsonParser] and mapped it to list of CityWeatherData, then update the
     * database with the data
     */
    private suspend fun updateCityWeatherDataInDb() {
        val parsedData = jsonParser.parseJson("weather_14.json")
        val mappedData = mapper.run { parsedData.toCityWeatherData() }
        localDataSource.storeCitiesWeatherDataInDb(mappedData)
    }
}