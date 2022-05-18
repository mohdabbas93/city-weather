package com.mohdabbas.cityweather

import com.mohdabbas.cityweather.data.Weather
import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData

/**
 * Created by Mohammad Abbas
 * On: 5/18/22.
 */
object TestUtil {

    val cityWeatherData1 = CityWeatherData(
        cityId = 1,
        cityName = "Test city1",
        country = "Test country",
        time = 1.0,
        temperature = 100.0,
        temperatureMin = 10.0,
        temperatureMax = 150.0,
        pressure = 100.0,
        humidity = 100.0,
        windSpeed = 40.0,
        weather = listOf(
            Weather(
                id = 1,
                main = "Sun",
                description = "Sunny",
                icon = "10d"
            )
        )
    )

    val cityWeatherData2 = CityWeatherData(
        cityId = 2,
        cityName = "Test city2",
        country = "Test country",
        time = 1.0,
        temperature = 100.0,
        temperatureMin = 10.0,
        temperatureMax = 150.0,
        pressure = 100.0,
        humidity = 100.0,
        windSpeed = 40.0,
        weather = listOf(
            Weather(
                id = 1,
                main = "Sun",
                description = "Sunny",
                icon = "10d"
            )
        )
    )

    val cityWeatherData3 = CityWeatherData(
        cityId = 3,
        cityName = "Test city3",
        country = "Test country",
        time = 1.0,
        temperature = 100.0,
        temperatureMin = 10.0,
        temperatureMax = 150.0,
        pressure = 100.0,
        humidity = 100.0,
        windSpeed = 40.0,
        weather = listOf(
            Weather(
                id = 1,
                main = "Sun",
                description = "Sunny",
                icon = "10d"
            )
        )
    )
}