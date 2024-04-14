package com.practice.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practice.myapplication.R
import com.practice.myapplication.fragment.ViewPagerTabFragment

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