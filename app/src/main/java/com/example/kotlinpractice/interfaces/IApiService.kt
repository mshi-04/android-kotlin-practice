package com.example.kotlinpractice.interfaces

import com.example.kotlinpractice.model.RootJson
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {
    @GET("")
    fun apiGet(): Call<RootJson>
}