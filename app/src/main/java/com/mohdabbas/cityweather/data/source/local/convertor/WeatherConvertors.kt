package com.mohdabbas.cityweather.data.source.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mohdabbas.cityweather.data.Weather

/**
 * Created by Mohammad Abbas
 * On: 5/17/22.
 */
class WeatherConvertors {

    @TypeConverter
    fun fromWeather(weather: List<Weather>): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toWeather(json: String): List<Weather> {
        return Gson().fromJson(json, Array<Weather>::class.java).toList()
    }
}