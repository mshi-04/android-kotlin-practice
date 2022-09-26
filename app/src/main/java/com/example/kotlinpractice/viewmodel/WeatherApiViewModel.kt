package com.example.kotlinpractice.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.kotlinpractice.R
import com.example.kotlinpractice.database.getDatabase
import com.example.kotlinpractice.model.DatabaseWeather
import com.example.kotlinpractice.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherApiViewModel(application: Application) : AndroidViewModel(application) {

    val weatherRepository = WeatherRepository(getDatabase(application))
    val weathers = weatherRepository.weathers
    val weatherList = MutableLiveData<List<DatabaseWeather>>()

    val eventNetworkError = MutableLiveData<Boolean>(false)


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherApiViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherApiViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }

    init {
        refreshDataFromRepository(application.getString(R.string.weather_url))
    }

    fun refreshDataFromRepository(url: String) {
        viewModelScope.launch {
            try {
                weatherRepository.refreshWeather(url)
            } catch (networkError: Exception) {
                if (weatherList.value.isNullOrEmpty()) eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        eventNetworkError.value = true
    }
}