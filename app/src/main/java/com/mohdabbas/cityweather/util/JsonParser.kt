package com.mohdabbas.cityweather.util

import android.content.Context
import com.google.gson.Gson
import com.mohdabbas.cityweather.data.CityWeather
import java.io.IOException


/**
 * Created by Mohammad Abbas
 * On: 5/14/22.
 */

class JsonParser(private val context: Context) {

    private val gson = Gson()

    fun parseJson(fileName: String): List<CityWeather> {
        // TODO: Make this function generic
        return readFileAsString(fileName).map { it.fromJson() }
    }

    private fun String.fromJson() = gson.fromJson(this, CityWeather::class.java)

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