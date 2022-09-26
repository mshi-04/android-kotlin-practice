package com.example.kotlinpractice.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinpractice.model.DatabaseWeather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM databaseweather")
    fun getWeather(): LiveData<List<DatabaseWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weathers: List<DatabaseWeather>)
}