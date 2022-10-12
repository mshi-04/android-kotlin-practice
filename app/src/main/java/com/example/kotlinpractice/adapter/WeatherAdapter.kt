package com.example.kotlinpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import com.example.kotlinpractice.databinding.ListItemWeatherBinding
import com.example.kotlinpractice.model.DatabaseWeather

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    var data = emptyList<DatabaseWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ListItemWeatherBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_weather,
            parent,
            false
        )
        return ViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.weather = data[position]
        }
    }

    override fun getItemCount() = data.size

    class ViewHolder(val viewDataBinding: ListItemWeatherBinding) : RecyclerView.ViewHolder(viewDataBinding.root)
}


