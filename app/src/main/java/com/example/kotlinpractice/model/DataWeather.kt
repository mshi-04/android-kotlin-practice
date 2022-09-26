package com.example.kotlinpractice.model

data class RootJson(val dataBody: List<DataBody>)

data class DataBody(
    val publishingOffice: String,
    val reportDatetime: String,
    val timeSeriesList: List<TimeSeries>
)

data class TimeSeries(
    var timeDefines: List<TimeDefine>,
    var areas: List<Areas>
)

data class TimeDefine(var timeStampString: String)

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