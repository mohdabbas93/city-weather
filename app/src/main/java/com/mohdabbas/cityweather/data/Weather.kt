package com.mohdabbas.cityweather.data

/**
 * Represents Weather.
 *
 * @property id The weather id.
 * @property main The main weather condition.
 * @property description The weather condition description.
 * @property icon The weather condition icon.
 */
data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)