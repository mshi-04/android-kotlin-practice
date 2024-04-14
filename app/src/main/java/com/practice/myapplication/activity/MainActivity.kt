package com.practice.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.practice.myapplication.databinding.ActivityMainBinding
import com.practice.myapplication.utility.HttpUtility
import com.practice.myapplication.utility.PushNotification
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    /**
     * private variable
     */
    private val TAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonFragmentStart.setOnClickListener {
            startActivity(Intent(applicationContext, FragmentLaunchActivity::class.java))
        }

        binding.buttonWebviewStart.setOnClickListener {
            startActivity(Intent(applicationContext, WebViewActivity::class.java))
        }

        binding.buttonSendIntent.setOnClickListener {
            val intent = Intent(applicationContext, ReceiveIntentActivity::class.java)
            intent.putExtra("SEND_TEXT", binding.edittextMessage.text.toString())
            startActivity(intent)
        }

        binding.buttonHttpRequest.setOnClickListener {
            lifecycleScope.launch {
                try {
                    HttpUtility.startHttpRequest()
                } catch (e: Exception) {
                    Log.i("エラー", e.toString())
                }
            }
        }

        binding.buttonSendPush.setOnClickListener {
            PushNotification(applicationContext).send()
        }

        binding.buttonAsync.setOnClickListener {
            startActivity(Intent(applicationContext, AsyncActivity::class.java))
        }

        binding.buttonApi.setOnClickListener {
            startActivity(Intent(applicationContext, WeatherApiActivity::class.java))
        }

        binding.buttonViewpager.setOnClickListener {
            startActivity(Intent(applicationContext, ViewPagerActivity::class.java))
        }

        binding.buttonSharedpreferences.setOnClickListener {
            startActivity(Intent(applicationContext, SharedPreferencesActivity::class.java))
        }
    }
}