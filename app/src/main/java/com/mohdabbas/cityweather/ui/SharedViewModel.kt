package com.mohdabbas.cityweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.data.CityWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A ViewModel for the [CityWeatherListFragment]
 */
@HiltViewModel
class SharedViewModel @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) :
    ViewModel() {

    private var _citiesWeather = MutableLiveData<List<CityWeather>>()
    val citiesWeather = _citiesWeather

    /**
     * Get the cities weather from the [CityWeatherRepository] and when retrieve
     * them post the result to the [_citiesWeather] mutable live data
     */
    fun getCitiesWeather() {
        viewModelScope.launch {
            val result = cityWeatherRepository.getCitiesWeather()
            _citiesWeather.postValue(result)
        }
    }

    /**
     * Search the list of cities by city name and return list of [CityWeather]
     *
     * @param name the name or part of the name of city/cities to be found
     * @return list of [CityWeather] if exist or empty list
     */
    fun searchByCityName(name: String): List<CityWeather> {
        return citiesWeather.value?.filter {
            it.city.findname.contains(name.trim(), ignoreCase = true)
        } ?: listOf()
    }

    /**
     * Search the list of cities by city id and return [CityWeather]
     *
     * @param cityId the id of the city weather to be found.
     * @return [CityWeather] if exist or null if not
     */
    fun getCityWeatherInfoByCityId(cityId: Int): CityWeather? {
        return citiesWeather.value?.firstOrNull { it.city.id == cityId }
    }
}