package com.mohdabbas.cityweather.util

/**
 * Combination of utility functions
 *
 * @author Mohammad Abbas
 */
object WeatherUtil {

    /**
     * Convert Temperature from Kelvin to Celsius
     *
     * @receiver a number of type [Double]
     * @return The result of the conversation
     */
    fun Double.temperatureFromKelvinToCelsius(): Int {
        return (this - 273.15).toInt()
    }

    /**
     * Convert speed from meter/sec to km/sec
     *
     * @receiver a number of type [Double]
     * @return The result of the conversation
     */
    fun Double.speedFromMeterPerSecToKmPerSec(): Int {
        return (this * 3.6).toInt()
    }
}