package com.mohdabbas.cityweather.data

import com.google.gson.annotations.SerializedName

/**
 * Represents Wind data.
 *
 * @property speed The wind speed.
 * @property deg The wind degree.
 * @property varBeg The var beginning.
 * @property varEnd The var end.
 */
data class Wind(
    val speed: Double,
    val deg: Double,
    @SerializedName("var_beg")
    val varBeg: Double,
    @SerializedName("var_end")
    val varEnd: Double
)