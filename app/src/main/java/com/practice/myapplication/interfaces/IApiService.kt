package com.practice.myapplication.interfaces

import com.practice.myapplication.model.WeatherBody
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {
    @GET("130000.json")
    fun getService(): Call<Array<WeatherBody>>
}