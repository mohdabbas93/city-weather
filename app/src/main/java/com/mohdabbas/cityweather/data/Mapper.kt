package com.mohdabbas.cityweather.data

import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData
import javax.inject.Inject

/**
 * Contain mapper functions
 *
 * @author Mohammad Abbas
 */
class Mapper @Inject constructor() {

    /**
     * The function is used to mapped a list of [CityWeather] to
     * [CityWeatherData]
     *
     * @receiver A list of type [CityWeather]
     * @return List of [CityWeatherData]
     */
    fun List<CityWeather>.toCityWeatherData() = map {
        CityWeatherData(
            cityId = it.city.id,
            cityName = it.city.name,
            country = it.city.country,
            time = it.time,
            temperature = it.main.temp,
            temperatureMin = it.main.tempMin,
            temperatureMax = it.main.tempMax,
            pressure = it.main.pressure,
            humidity = it.main.humidity,
            windSpeed = it.wind.speed,
            weather = it.weather
        )
    }

    /**
     * The function is used to mapped a list of [CityWeatherData] to
     * [CityWeather]
     *
     * @receiver A list of type [CityWeatherData]
     * @return List of [CityWeather]
     */
    fun List<CityWeatherData>.toCityWeather() = map { it.toCityWeather() }

    /**
     * The function is used to mapped  [CityWeatherData] to
     * [CityWeather]
     *
     * @receiver A type of [CityWeatherData]
     * @return  [CityWeather]
     */
    fun CityWeatherData.toCityWeather() = CityWeather(
        city = City(
            id = cityId,
            name = cityName,
            findname = "",
            country = country,
            coord = Coord(0.0, 0.0),
            zoom = 0
        ),
        time = time,
        main = Main(
            temp = temperature,
            pressure = pressure,
            humidity = humidity,
            tempMin = temperatureMin,
            tempMax = temperatureMax
        ),
        wind = Wind(
            speed = windSpeed,
            deg = 0.0,
            varBeg = 0.0,
            varEnd = 0.0
        ),
        clouds = Clouds(0.0),
        weather = weather
    )
}