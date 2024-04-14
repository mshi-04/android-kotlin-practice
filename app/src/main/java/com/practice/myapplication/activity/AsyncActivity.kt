package com.practice.myapplication.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.practice.myapplication.databinding.ActivityAsyncBinding
import com.practice.myapplication.viewmodel.AsyncViewModel

class AsyncActivity : AppCompatActivity() {

    private lateinit var viewModel: AsyncViewModel
    private lateinit var binding: ActivityAsyncBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = AsyncViewModel()

        binding = ActivityAsyncBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonExecutor.setOnClickListener {
            viewModel.executorStart()
        }
    }
}