package com.example.kotlinpractice.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ActivityMainBinding
import com.example.kotlinpractice.utility.HttpUtility
import com.example.kotlinpractice.utility.PushNotification
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    /**
     * private variable
     */
    private val TAG: String = "MainActivity"
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
            val intent: Intent = Intent(applicationContext, ReceiveIntentActivity::class.java)
            intent.putExtra("SEND_TEXT", binding.edittextMessage.text.toString())
            startActivity(intent)
        }

        binding.buttonHttpRequest.setOnClickListener {
            val hU: HttpUtility = HttpUtility()
            lifecycleScope.launch {
                try {
                    hU.startHttpRequest()
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
    }
}