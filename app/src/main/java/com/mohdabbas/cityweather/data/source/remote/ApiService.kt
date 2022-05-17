package com.mohdabbas.cityweather.data.source.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

/**
 * Represent the HTTP API
 *
 * @author Mohammad Abbas
 */
interface ApiService {

    /**
     *  Used to download the file from the URL specifies in the [GET] annotation.
     *
     *  @return The ResponseBody that containing the file to be downloaded.
     */
    @GET("sample/weather_14.json.gz")
    suspend fun downloadFileAndGetBodyResponse(): Response<ResponseBody>
}