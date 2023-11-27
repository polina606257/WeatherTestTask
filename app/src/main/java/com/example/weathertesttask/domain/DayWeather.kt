package com.example.weathertesttask.domain

import com.google.gson.annotations.SerializedName

data class DayWeather(
    val dt: Long,
    val main: MainWeatherInfo,
    val weather: List<WeatherDefinition>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
)