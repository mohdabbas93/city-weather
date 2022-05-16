package com.mohdabbas.cityweather.data

import com.mohdabbas.cityweather.util.JsonParser
import javax.inject.Inject

/**
 * The repository to get the cities weather
 *
 * @author Mohammad Abbas
 * @property jsonParser an instance of the [JsonParser] class.
 */
class CityWeatherRepository @Inject constructor(private val jsonParser: JsonParser) {

    /**
     *  Uses to get the city by using the [JsonParser] class to get
     *  the list of cities weather
     *
     * @return a list of [CityWeather].
     */
    fun getCitiesWeather(): List<CityWeather> {
        return jsonParser.parseJson("weather_14.json")
    }
}



