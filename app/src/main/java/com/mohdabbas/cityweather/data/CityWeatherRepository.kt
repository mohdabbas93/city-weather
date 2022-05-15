package com.mohdabbas.cityweather.data

import com.mohdabbas.cityweather.util.JsonParser

/**
 * Created by Mohammad Abbas
 * On: 5/15/22.
 */
class CityWeatherRepository(private val jsonParser: JsonParser) {

    fun getCitiesWeather(): List<CityWeather> {
        return jsonParser.parseJson("weather_14.json")
    }
}



