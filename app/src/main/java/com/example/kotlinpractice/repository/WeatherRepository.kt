package com.example.kotlinpractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlinpractice.database.WeatherDatabase
import com.example.kotlinpractice.model.DatabaseWeather
import com.example.kotlinpractice.model.RootJson
import com.example.kotlinpractice.model.asDomainModel
import com.example.kotlinpractice.utility.HttpUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class WeatherRepository(private val database: WeatherDatabase) {

    val weathers: LiveData<List<DatabaseWeather>> =
        Transformations.map(database.WeatherDao.getWeather()) {
            it.asDomainModel()
        }

    suspend fun refreshWeather(url: String) {
        withContext(Dispatchers.IO) {
            try {
                val weatherJson = HttpUtility.createRetrofit(url).apiGet()
                database.WeatherDao.insertAll(analyzeJson(weatherJson))
            } catch (e: Exception) {

            }
        }
    }

    private fun analyzeJson(weatherJson: Call<RootJson>) : List<DatabaseWeather> {
        val response = weatherJson.execute().body()
        val body = response?.dataBody?.get(0)
        val weatherDatas = body?.timeSeriesList?.get(0)
        val reportDatetime = body?.reportDatetime
        val timeDefines = weatherDatas?.timeDefines
        val areas = weatherDatas?.areas

        val weatherList = listOf<DatabaseWeather>()

        var index = 0
        timeDefines?.forEach {
            val entity = DatabaseWeather(
                it.timeStampString,
                areas?.get(0)?.area?.name,
                areas?.get(0)?.area?.code,
                areas?.get(0)?.weatherCodes?.get(index),
                areas?.get(0)?.weathers?.get(index),
                areas?.get(0)?.winds?.get(index),
                areas?.get(0)?.waves?.get(index),
                reportDatetime
            )
            weatherList.plus(entity)
            index++
        }
        return weatherList
    }
}