package com.mohdabbas.cityweather.data.source.remote

import javax.inject.Inject

/**
 * Represent the the remote data source and will be the entry point to make
 * remote calls
 *
 * @author Mohammad Abbas
 * @property apiService The Api Service
 */
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    /**
     * The function used to download the cities weather data and get
     * the Response Body
     *
     * @return The response body
     */
    suspend fun downloadCitiesWeatherDataAndGetResponseBody() =
        apiService.downloadFileAndGetBodyResponse()
}