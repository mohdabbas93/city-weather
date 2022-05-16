package com.mohdabbas.cityweather.data

import com.google.gson.annotations.SerializedName

/**
 * Represents the main weather.
 *
 * @property temp The current temperature.
 * @property pressure The current pressure.
 * @property humidity The current humidity..
 * @property tempMin The current minimum temperature.
 * @property tempMax The current maximum temperature.
 */
class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)