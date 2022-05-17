package com.mohdabbas.cityweather.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohdabbas.cityweather.data.Weather

/**
 * Represents city weather table.
 *
 * @property cityId City ID
 * @property cityName City name
 * @property country Country name
 * @property time Time of data calculation, unix, UTC
 * @property temperature Temperature. Unit Default: Kelvin,
 * @property temperatureMin Minimum temperature at the moment, Unit Default: Kelvin
 * @property temperatureMax Maximum temperature at the moment, Unit Default: Kelvin
 * @property pressure Atmospheric pressure, hPa
 * @property humidity Humidity, %
 * @property windSpeed Wind speed. Unit Default: meter/sec
 * @property weather List of weather conditions
 *
 */
@Entity(tableName = "city_weather")
data class CityWeatherData(
    @ColumnInfo(name = "city_id")
    @PrimaryKey
    val cityId: Int,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    val country: String,
    val time: Double,
    val temperature: Double,
    @ColumnInfo(name = "temperature_min")
    val temperatureMin: Double,
    @ColumnInfo(name = "temperature_max")
    val temperatureMax: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weather: List<Weather>
)