package com.example.kotlinpractice.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.kotlinpractice.R
import com.example.kotlinpractice.fragment.SampleFragment

class FragmentLaunchActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_launch)

        val fragment: SampleFragment = SampleFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment)
        transaction.commit()
    }
}