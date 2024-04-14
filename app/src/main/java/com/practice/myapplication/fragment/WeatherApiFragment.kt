package com.practice.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.myapplication.R
import com.practice.myapplication.adapter.WeatherAdapter
import com.practice.myapplication.databinding.FragmentWeatherApiBinding
import com.practice.myapplication.viewmodel.WeatherApiViewModel

class WeatherApiFragment : Fragment() {

    private lateinit var binding: FragmentWeatherApiBinding
    private var viewModelAdapter: WeatherAdapter? = null

    private val viewModel: WeatherApiViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, WeatherApiViewModel.Factory(activity.application))
            .get(WeatherApiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_api,
            container,
            false
        )

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it) onNetworkError()
        })

        initView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weathers.observe(viewLifecycleOwner) {
            it?.apply {
                viewModelAdapter?.data = it
            }
        }
    }

    fun initView() {
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel
        viewModelAdapter = WeatherAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.weatherRefreshButton.setOnClickListener {
            getWeatherData()
        }
    }

    private fun getWeatherData() {
        viewModel.refreshDataFromRepository(resources.getString(R.string.weather_url))
    }

    private fun onNetworkError() {
        if (!viewModel.eventNetworkError.value!!) {
            Toast.makeText(activity, "通信エラー", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}