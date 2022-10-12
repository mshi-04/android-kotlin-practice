package com.example.kotlinpractice.interfaces

import com.example.kotlinpractice.model.WeatherBody
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {
    @GET("130000.json")
    fun getService(): Call<Array<WeatherBody>>
}