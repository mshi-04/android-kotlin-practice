package com.example.kotlinpractice.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpractice.databinding.ActivityReceiveIntentBinding

class ReceiveIntentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiveIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveIntentBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val text = intent.getStringExtra("SEND_TEXT")
        binding.textMessageReply.text = text
    }

}