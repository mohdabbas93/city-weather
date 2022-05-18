package com.mohdabbas.cityweather.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mohdabbas.cityweather.TestUtil
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Mohammad Abbas
 * On: 5/18/22.
 */

@RunWith(AndroidJUnit4::class)
class CityWeatherDaoTest {

    private lateinit var database: CityWeatherDatabase
    private lateinit var dao: CityWeatherDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CityWeatherDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.cityWeatherDao()
    }


    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun addAndGetCitiesWeatherData() = runBlocking {
        val cityWeatherData = TestUtil.cityWeatherData1

        dao.insertCitiesWeatherData(listOf(cityWeatherData))

        val result = dao.getCitiesWeatherData()

        assertThat(result).isEqualTo(listOf(cityWeatherData))
    }

    @Test
    fun searchCityWeatherDataByCityId() = runBlocking {
        val citiesWeatherData =
            listOf(TestUtil.cityWeatherData1, TestUtil.cityWeatherData2, TestUtil.cityWeatherData3)

        dao.insertCitiesWeatherData(citiesWeatherData)

        val result = dao.getCityWeatherByCityId(TestUtil.cityWeatherData2.cityId)

        assertThat(result).isEqualTo(TestUtil.cityWeatherData2)
    }

    @Test
    fun searchCitiesWeatherDataByCityName() = runBlocking {
        val cityWeatherData =
            listOf(TestUtil.cityWeatherData1, TestUtil.cityWeatherData2, TestUtil.cityWeatherData3)

        dao.insertCitiesWeatherData(cityWeatherData)

        val result = dao.getCitiesWeatherByCityName(TestUtil.cityWeatherData3.cityName.trim())

        assertThat(result).contains(TestUtil.cityWeatherData3)
    }
}