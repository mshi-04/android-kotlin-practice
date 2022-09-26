package com.example.kotlinpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseWeather(
    @PrimaryKey
    var timeDefine: String,
    var name: String?,
    var code: String?,
    var weatherCode: String?,
    var weather: String?,
    var winds: String?,
    var waves: String?,
    var reportDatetime: String?
)

fun List<DatabaseWeather>.asDomainModel(): List<DatabaseWeather> {
    return map {
        DatabaseWeather(
            timeDefine = it.timeDefine,
            name = it.name,
            code = it.code,
            weatherCode = it.weatherCode,
            weather = it.weather,
            winds = it.winds,
            waves = it.winds,
            reportDatetime = it.reportDatetime
        )
    }
}