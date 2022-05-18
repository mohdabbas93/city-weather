package com.mohdabbas.cityweather.ui.details

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
 * A ViewModel for the [CityWeatherDetailsFragment]
 *
 * @author Mohammad Abbas
 */
@HiltViewModel
class CityWeatherDetailsViewModel @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) : ViewModel() {

    private var _cityWeatherDetails = MutableLiveData<Result<CityWeather>>()
    val cityWeatherDetails: LiveData<Result<CityWeather>> = _cityWeatherDetails

    private var cityWeatherDetailsJob: Job? = null

    /**
     * Search the city weather by city id and return [CityWeather]
     *
     * @param cityId the id of the city weather to be found.
     */
    fun getCityWeatherInfoByCityId(cityId: Int) {
        cityWeatherDetailsJob?.cancel()

        viewModelScope.launch(Dispatchers.IO) {
            _cityWeatherDetails.postValue(Result.Loading)
            val result = cityWeatherRepository.getCityWeatherByCityId(cityId)
            _cityWeatherDetails.postValue(Result.Success(result))
        }
    }
}