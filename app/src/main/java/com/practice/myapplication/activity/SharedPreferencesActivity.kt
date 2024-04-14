package com.practice.myapplication.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.practice.myapplication.R
import com.practice.myapplication.databinding.ActivitySharedpreferencesBinding
import com.practice.myapplication.viewmodel.SharedPreferencesViewModel

class SharedPreferencesActivity : AppCompatActivity() {

    private val TAG = SharedPreferencesActivity::class.simpleName

    private lateinit var viewModel: SharedPreferencesViewModel
    private lateinit var binding: ActivitySharedpreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

        viewModel = SharedPreferencesViewModel()

        binding = ActivitySharedpreferencesBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {
        Log.i(TAG, "initView")
        initEditText()
        initRadio()
        initSpinner()
    }

    private fun initEditText() {
        binding.shaEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i(TAG, "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i(TAG, "onTextChanged")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged")
            }
        })
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).apply {
            this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.planetSpinner.adapter = this
        }

        binding.planetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.i(TAG, "onItemSelected")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i(TAG, "onNothingSelected")
            }
        }
    }

    private fun initRadio() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.i(TAG, "onCheckedChange")
        }
    }
}