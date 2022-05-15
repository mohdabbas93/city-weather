package com.mohdabbas.cityweather.data

data class City(
    val id: Int,
    val name: String,
    val findname: String,
    val country: String,
    val coord: Coord,
    val zoom: Int
)