package com.example.kotlinpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.FragmentWeatherApiBinding
import com.example.kotlinpractice.databinding.ListItemWeatherBinding
import com.example.kotlinpractice.model.DatabaseWeather

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    var data = emptyList<DatabaseWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_weather

        )
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = data.size

    class ViewHolder(viewDataBinding: ListItemWeatherBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        val textTime: TextView = viewDataBinding.textTime
        val textWeatherStatus: TextView
        val textWinds: TextView
        val textWaves: TextView

        init {

        }
    }
}


