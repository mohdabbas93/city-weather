package com.mohdabbas.cityweather.data

import com.google.gson.Gson
import com.mohdabbas.cityweather.data.source.local.LocalDataSource
import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData
import com.mohdabbas.cityweather.data.source.remote.RemoteDataSource
import com.mohdabbas.cityweather.util.GZIPUtil
import com.mohdabbas.cityweather.util.InternalStorageReadWriteUtil
import javax.inject.Inject

/**
 * The repository to interact with the cities weather data
 *
 * @author Mohammad Abbas
 * @property localDataSource an instance of the [LocalDataSource] class
 * @property remoteDataSource an instance of the [RemoteDataSource] class
 * @property internalStorageReadWrite an instance of the [InternalStorageReadWriteUtil] class
 * @property gzipUtil an instance of the [GZIPUtil] class
 * @property mapper an instance of the [Mapper] class
 */
class CityWeatherRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val internalStorageReadWrite: InternalStorageReadWriteUtil,
    private val gzipUtil: GZIPUtil,
    private val mapper: Mapper
) {

    /**
     *  Used to get the list of [CityWeather] from the database.
     *
     *  If the result from the database is empty the data will be parsed
     *  from the Json file in the assets folder and after that will be
     *  stored in the database and get the list of [CityWeather] again.
     *
     * @return a list of [CityWeather].
     */
    suspend fun getCitiesWeather(): List<CityWeather> {
        val citiesWeather = localDataSource.getCitiesWeatherData()

        return if (citiesWeather.isEmpty()) {
            updateCityWeatherDataInDb()
            mapper.run { localDataSource.getCitiesWeatherData().toCityWeather() }
        } else {
            mapper.run { citiesWeather.toCityWeather() }
        }
    }

    /**
     * Used to download the zip file, extract the json file that contain the city weather data,
     * and store it in the internal storage, then read again and parse it using [Gson] to a list
     * of [CityWeather], then mapped the a list to [CityWeatherData] to be stored in the database.
     */
    private suspend fun updateCityWeatherDataInDb() {

        val gzipInputStream =
            remoteDataSource.downloadCitiesWeatherDataAndGetResponseBody().body()?.byteStream()

        val citiesWeather = gzipInputStream?.let { inputStream ->
            val gZIPInputStream = gzipUtil.getGZIPInputStream(inputStream)

            val ndJsonFilePath =
                internalStorageReadWrite.writeFileToInternalStorage(gZIPInputStream)

            val ndJsonFile = internalStorageReadWrite.readFileFromInternalStorage(ndJsonFilePath)

            val jsonDataAsStringList = ndJsonFile.bufferedReader().readLines()

            jsonDataAsStringList.map { Gson().fromJson(it, CityWeather::class.java) }
        } ?: listOf()

        val mappedData = mapper.run { citiesWeather.toCityWeatherData() }
        localDataSource.storeCitiesWeatherDataInDb(mappedData)
    }

    /**
     * The function is used to get the data of the cities weather from the local data source
     * by city name and map the result to city weather
     *
     * @return List of cities weather
     */
    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeather> {
        return mapper.run { localDataSource.getCitiesWeatherByCityName(cityName).toCityWeather() }
    }
}