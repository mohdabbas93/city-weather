package com.mohdabbas.cityweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.data.CityWeatherRepository
import com.mohdabbas.cityweather.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A ViewModel for the [CityWeatherListFragment]
 */
@HiltViewModel
class SharedViewModel @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) :
    ViewModel() {

    private var _citiesWeather = MutableLiveData<Result<List<CityWeather>>>()
    val citiesWeather: LiveData<Result<List<CityWeather>>> = _citiesWeather

    /**
     * Get the cities weather from the [CityWeatherRepository] and when retrieve
     * them post the result to the [_citiesWeather] mutable live data
     */
    fun getCitiesWeather() {
        val cashedData = getCachedData()
        if (cashedData.isNotEmpty()) {
            _citiesWeather.value = Result.Success(cashedData)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _citiesWeather.postValue(Result.Loading)
            val result = cityWeatherRepository.getCitiesWeather()
            _citiesWeather.postValue(Result.Success(result))
        }
    }

    /**
     *  Return a cashed version of the data instead of requesting the same data from
     *  the database everytime to page is navigated to
     *
     *  @return List of [CityWeather]
     */
    private fun getCachedData(): List<CityWeather> {
        _citiesWeather.value?.let { result ->
            if (result is Result.Success) {
                return result.data
            }
        }

        return emptyList()
    }

    private var _searchedCitiesWeather = MutableLiveData<Result<List<CityWeather>>>()
    val searchedCitiesWeather: LiveData<Result<List<CityWeather>>> = _searchedCitiesWeather

    private var searchJob: Job? = null

    /**
     * Search the list of cities by city name and return list of [CityWeather]
     *
     * @param name the name or part of the name of city/cities to be found
     */
    fun searchByCityName(name: String) {
        searchJob?.cancel()

        if (name.isBlank()) {
            _searchedCitiesWeather.value = _citiesWeather.value
            return
        }

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _searchedCitiesWeather.postValue(Result.Loading)
            val result = cityWeatherRepository.getCitiesWeatherByCityName(name.trim())
            _searchedCitiesWeather.postValue(Result.Success(result))
        }
    }

    /**
     * Search the list of cities by city id and return [CityWeather]
     *
     * @param cityId the id of the city weather to be found.
     * @return [CityWeather] if exist or null if not
     */
    fun getCityWeatherInfoByCityId(cityId: Int): CityWeather? {
        val citiesWeatherResult = citiesWeather.value
        return if (citiesWeatherResult is Result.Success) {
            citiesWeatherResult.data.firstOrNull { it.city.id == cityId }
        } else {
            null
        }
    }
}