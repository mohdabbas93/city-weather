package com.mohdabbas.cityweather.util

import com.google.common.truth.Truth.assertThat
import com.mohdabbas.cityweather.util.WeatherUtil.speedFromMeterPerSecToKmPerHour
import com.mohdabbas.cityweather.util.WeatherUtil.temperatureFromKelvinToCelsius
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Mohammad Abbas
 * On: 5/16/22.
 */
@RunWith(JUnit4::class)
class WeatherUtilTest {

    @Test
    fun should_success_when_temperatureFromKelvinToCelsiusConversionIsCorrect() {
        val temperatureInKelvin = 300.0
        val temperatureInCelsiusCorrectResult = 26
        val temperatureInCelsiusCalculatedResult =
            temperatureInKelvin.temperatureFromKelvinToCelsius()

        assertThat(temperatureInCelsiusCorrectResult).isEqualTo(temperatureInCelsiusCalculatedResult)
    }

    @Test
    fun should_fail_when_temperatureFromKelvinToCelsiusConversionIsIncorrect() {
        val temperatureInKelvin = 300.0
        val temperatureInCelsiusIncorrectResult = 40
        val temperatureInCelsiusCalculatedResult =
            temperatureInKelvin.temperatureFromKelvinToCelsius()

        assertThat(temperatureInCelsiusIncorrectResult).isNotEqualTo(
            temperatureInCelsiusCalculatedResult
        )
    }

    @Test
    fun should_success_when_speedFromMeterPerSecToKmPerHourConversionIsCorrect() {
        val speedInMeterPerSec = 5.0
        val speedInKmPerHourCorrectResult = 18
        val speedInKmPerHourCorrectCalculatedResult =
            speedInMeterPerSec.speedFromMeterPerSecToKmPerHour()

        assertThat(speedInKmPerHourCorrectCalculatedResult).isEqualTo(
            speedInKmPerHourCorrectResult
        )
    }

    @Test
    fun should_fail_when_speedFromMeterPerSecToKmPerHourConversionIsInCorrect() {
        val speedInMeterPerSec = 5.0
        val speedInKmPerHourIncorrectResult = 40
        val speedInKmPerHourCorrectCalculatedResult =
            speedInMeterPerSec.speedFromMeterPerSecToKmPerHour()

        assertThat(speedInKmPerHourCorrectCalculatedResult).isNotEqualTo(
            speedInKmPerHourIncorrectResult
        )
    }

}