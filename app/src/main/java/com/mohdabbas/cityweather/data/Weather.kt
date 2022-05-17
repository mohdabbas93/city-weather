package com.mohdabbas.cityweather.data

/**
 * Represents weather condition.
 *
 * @property id Weather condition id
 * @property main Group of weather parameters (Rain, Snow, Extreme etc.)
 * @property description Weather condition within the group.
 * @property icon Weather icon id
 */
data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)