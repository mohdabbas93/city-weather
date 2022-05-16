package com.mohdabbas.cityweather.data

/**
 * Represents a City weather.
 *
 * @property city The [City].
 * @property time The current time.
 * @property main The [Main].
 * @property wind The [Wind].
 * @property cloud The [Clouds].
 * @property weather The [Weather].
 */
data class CityWeather(
    val city: City,
    val time: Double,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val weather: List<Weather>,
)