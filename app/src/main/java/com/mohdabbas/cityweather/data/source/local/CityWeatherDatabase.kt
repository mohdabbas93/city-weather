package com.mohdabbas.cityweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohdabbas.cityweather.data.source.local.convertor.WeatherConvertors
import com.mohdabbas.cityweather.data.source.local.entity.CityWeatherData

/**
 *  The City Weather Database
 *
 * @author Mohammad Abbas
 */
@Database(entities = [CityWeatherData::class], version = 1)
@TypeConverters(WeatherConvertors::class)
abstract class CityWeatherDatabase : RoomDatabase() {

    /**
     *  This function is used to get the city weather Dao
     *
     *  @return [CityWeatherDao]
     */
    abstract fun cityWeatherDao(): CityWeatherDao
}