package com.example.kotlinpractice.utility

import android.util.Log
import com.example.kotlinpractice.interfaces.IApiService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

object HttpUtility {

    suspend fun startHttpRequest() {
        val url = URL("https://google.co.jp")
        return withContext(Dispatchers.IO) {
            (url.openConnection() as? HttpURLConnection)?.run {
                requestMethod = "GET"

                doOutput = true
                doInput = true

                Log.i("HTTP", "responseCode = ${responseCode}")
                Log.i("HTTP", "HttpURLConnection Success")
                return@withContext
            }
            Log.i("HTTP", "Cannot open HttpURLConnection")
        }
    }

    fun createRetrofit(url: String): IApiService {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .baseUrl(url)
            .build()

        return retrofit.create(IApiService::class.java)
    }
}