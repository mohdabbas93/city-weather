package com.mohdabbas.cityweather.data

import com.google.gson.annotations.SerializedName

class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)