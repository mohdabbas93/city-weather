package com.mohdabbas.cityweather.data

/**
 * Represents a City.
 *
 * @property id The city id.
 * @property name The city name.
 * @property findname The city name.
 * @property country The country of the city.
 * @property coord The [Coord] of the city.
 * @property zoom The zoom.
 */
data class City(
    val id: Int,
    val name: String,
    val findname: String,
    val country: String,
    val coord: Coord,
    val zoom: Int
)