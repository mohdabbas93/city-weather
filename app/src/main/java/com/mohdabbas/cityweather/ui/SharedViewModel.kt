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
 * Created by Mohammad Abbas
 * On: 5/15/22.
 */
@HiltViewModel
class SharedViewModel @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) :
    ViewModel() {

    private var _citiesWeather = MutableLiveData<List<CityWeather>>()
    val citiesWeather = _citiesWeather

    fun getCitiesWeather() {
        viewModelScope.launch {
            val result = cityWeatherRepository.getCitiesWeather()
            _citiesWeather.postValue(result)
        }
    }

    fun searchByCityName(name: String): List<CityWeather> {
        return citiesWeather.value?.filter {
            it.city.findname.contains(name.trim(), ignoreCase = true)
        } ?: listOf()
    }

    fun getCityWeatherInfoByCityId(cityId: Int): CityWeather? {
        return citiesWeather.value?.firstOrNull { it.city.id == cityId }
    }
}