package com.example.kotlinpractice.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpractice.R
import com.example.kotlinpractice.viewpager.ViewPagerTabFragment

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        val fragment = ViewPagerTabFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }
}