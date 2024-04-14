package com.practice.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID

class OnetimePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uniqueId = UUID.randomUUID().toString()


    }
}