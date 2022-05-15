package com.mohdabbas.cityweather.data

import com.google.gson.annotations.SerializedName

data class Wind(
    val speed: Double,
    val deg: Double,
    @SerializedName("var_beg")
    val varBeg: Double,
    @SerializedName("var_end")
    val varEnd: Double
)