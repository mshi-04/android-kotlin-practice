package com.example.kotlinpractice.utility

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class HttpUtility {

    suspend fun startHttpRequest() {
        val url = URL("https://www.google.com/")
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
}