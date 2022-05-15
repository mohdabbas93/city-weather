package com.mohdabbas.cityweather.data

data class CityWeather(
    val city: City,
    val time: Double,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val weather: List<Weather>,
)