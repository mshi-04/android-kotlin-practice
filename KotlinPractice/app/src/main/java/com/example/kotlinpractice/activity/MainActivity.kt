package com.example.kotlinpractice.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlinpractice.databinding.ActivityMainBinding
import com.example.kotlinpractice.utility.PushNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    /**
     * private variable
     */
    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonSendIntent.setOnClickListener {

        }

        binding.buttonFragmentStart.setOnClickListener {
            startActivity(Intent(applicationContext, FragmentLaunchActivity::class.java))
        }

        binding.buttonWebviewStart.setOnClickListener {
            startActivity(Intent(applicationContext, WebViewActivity::class.java))
        }

        binding.buttonHttpRequest.setOnClickListener {
            lifecycleScope.launch {
                try {
                    startHttpRequest()
                } catch (e: Exception) {
                    Log.i("エラー", e.toString())
                }
            }
        }

        binding.buttonSendPush.setOnClickListener {
            PushNotification(applicationContext).send()
        }
    }

    private suspend fun startHttpRequest() {
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