package com.mohdabbas.cityweather.data

import com.mohdabbas.cityweather.util.JsonParser
import javax.inject.Inject

/**
 * Created by Mohammad Abbas
 * On: 5/15/22.
 */
class CityWeatherRepository @Inject constructor(private val jsonParser: JsonParser) {

    fun getCitiesWeather(): List<CityWeather> {
        return jsonParser.parseJson("weather_14.json")
    }
}



