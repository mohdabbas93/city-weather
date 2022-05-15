package com.mohdabbas.cityweather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.data.CityWeatherRepository
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Abbas
 * On: 5/15/22.
 */
class SharedViewModel(private val cityWeatherRepository: CityWeatherRepository) : ViewModel() {

    private var _citiesWeather = MutableLiveData<List<CityWeather>>()
    val citiesWeather = _citiesWeather

    fun getCitiesWeather() {
        viewModelScope.launch {
            val result = cityWeatherRepository.getCitiesWeather()
            _citiesWeather.postValue(result)
        }
    }
}