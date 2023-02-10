package com.example.kotlinpractice.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class OnetimePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uniqueId = UUID.randomUUID().toString()


    }
}