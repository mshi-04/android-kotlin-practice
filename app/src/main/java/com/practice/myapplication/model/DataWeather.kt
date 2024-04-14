package com.practice.myapplication.model

data class WeatherBody(
    val publishingOffice: String,
    val reportDatetime: String,
    val timeSeries: List<TimeSeries>
)

data class TimeSeries(
    var timeDefines: List<String>,
    var areas: List<Areas>
)

data class Areas(
    var area: Area,
    var weatherCodes: List<String>,
    var weathers: List<String>,
    var winds: List<String>,
    var waves: List<String>,
)

data class Area(
    var name: String,
    var code: String
)