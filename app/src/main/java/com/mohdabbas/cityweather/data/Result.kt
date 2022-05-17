package com.mohdabbas.cityweather.data

/**
 *  A generic class that holds a value with its loading state
 *
 * @author Mohammad Abbas
 * @param T the type of data of this result
 */

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}