package com.practice.myapplication.repository

import androidx.lifecycle.LiveData
import com.practice.myapplication.database.WeatherDatabase
import com.practice.myapplication.model.DatabaseWeather
import com.practice.myapplication.model.WeatherBody
import com.practice.myapplication.utility.HttpUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WeatherRepository(private val database: WeatherDatabase) {

    lateinit var weathers: LiveData<List<DatabaseWeather>>

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