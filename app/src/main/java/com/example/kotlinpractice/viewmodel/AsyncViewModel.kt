package com.example.kotlinpractice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AsyncViewModel : ViewModel() {

    public fun executorStart() {
        val executor:ExecutorService = Executors.newSingleThreadExecutor()

        executor.execute {
            Log.i("####", "サブスレッド")
        }
    }
}