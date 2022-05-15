package com.mohdabbas.cityweather.util

/**
 * Created by Mohammad Abbas
 * On: 5/15/22.
 */
object WeatherUtil {

    fun Double.temperatureFromKelvinToCelsius(): Int {
        return (this - 273.15).toInt()
    }

    fun Double.speedFromMeterPerSecToKmPerSec(): Int {
        return (this * 3.6).toInt()
    }
}