package com.practice.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.practice.myapplication.R
import com.practice.myapplication.fragment.WeatherApiFragment

class WeatherApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_api)

        val fragment = WeatherApiFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()
    }
}