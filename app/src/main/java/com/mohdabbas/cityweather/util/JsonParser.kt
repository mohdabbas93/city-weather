package com.mohdabbas.cityweather.util

import android.content.Context
import com.google.gson.Gson
import com.mohdabbas.cityweather.data.CityWeather
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject


/**
 *  Uses to parse a file to an object.
 *
 * @author Mohammad Abbas
 * @property context as the application context
 */

class JsonParser @Inject constructor(@ApplicationContext private val context: Context) {

    private val gson = Gson()

    /**
     *  Uses to read the file using the [fileName] and get a [List] of [String]
     *  and parse them into [CityWeather]
     *
     * @param fileName String of the file name to be parsed.
     * @return a list of [CityWeather].
     */
    fun parseJson(fileName: String): List<CityWeather> {
        // TODO: Make this function generic
        return readFileAsString(fileName).map { it.fromJson() }
    }

    /**
     *  Uses <b>Gson<b> which is a third party library to deserializes
     *  the specified Json into an object of the specified [CityWeather].
     *
     * @receiver [String]
     * @return an object of type [CityWeather] from the string.
     */

    private fun String.fromJson() = gson.fromJson(this, CityWeather::class.java)

    /**
     * Read a file from the assets as buffered input stream and
     * read it line by line a string and return list of strings
     *
     * @param fileName as String as the name of the file to be opened
     * @return List of strings
     * @throws IOException if reading the file failed
     */
    private fun readFileAsString(fileName: String): List<String> {
        var result = listOf<String>()

        try {
            result = context.assets.open(fileName)
                .bufferedReader()
                .readLines()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}