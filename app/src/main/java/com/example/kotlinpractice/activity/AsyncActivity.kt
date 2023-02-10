package com.example.kotlinpractice.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpractice.databinding.ActivityAsyncBinding
import com.example.kotlinpractice.viewmodel.AsyncViewModel

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