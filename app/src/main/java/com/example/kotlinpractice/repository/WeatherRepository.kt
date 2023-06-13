package com.example.kotlinpractice.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlinpractice.database.WeatherDatabase
import com.example.kotlinpractice.model.DatabaseWeather
import com.example.kotlinpractice.model.WeatherBody
import com.example.kotlinpractice.model.asDomainModel
import com.example.kotlinpractice.utility.HttpUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherRepository(private val database: WeatherDatabase) {

    val weathers: LiveData<List<DatabaseWeather>> =
        Transformations.map(database.WeatherDao.getWeather()) {
            it.asDomainModel()
        }

    suspend fun refreshWeather(url: String) {
        withContext(Dispatchers.IO) {
            try {
                val service = HttpUtility.createRetrofit(url).getService()
                val response = service.execute()
                if (response.isSuccessful) {
                    database.WeatherDao.insertAll(analyzeJson(response))
                }
            } catch (e: Exception) {
                println(e.stackTraceToString())
            }
        }
    }

    private fun analyzeJson(response: Response<Array<WeatherBody>>): List<DatabaseWeather> {
        val body = response.body()
        val dataBody = body?.get(0)

        val reportDatetime = dataBody?.reportDatetime
        val timeSeriesList = dataBody?.timeSeries

        val timeDefines = timeSeriesList?.get(0)?.timeDefines
        val areas = timeSeriesList?.get(0)?.areas

        val weatherList = mutableListOf<DatabaseWeather>()
        var index = 0
        timeDefines?.forEach {
            val entity = DatabaseWeather(
                it,
                areas?.get(0)?.area?.name,
                areas?.get(0)?.area?.code,
                areas?.get(0)?.weatherCodes?.get(index),
                areas?.get(0)?.weathers?.get(index),
                areas?.get(0)?.winds?.get(index),
                areas?.get(0)?.waves?.get(index),
                reportDatetime
            )
            weatherList.add(entity)
            index++
        }
        return weatherList
    }
}