package com.practice.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.myapplication.dao.WeatherDao
import com.practice.myapplication.model.DatabaseWeather

@Database(entities = [DatabaseWeather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val WeatherDao: WeatherDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weather"
            ).build()
        }
    }
    return INSTANCE
}