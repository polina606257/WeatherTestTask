package com.example.weathertesttask.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HourlyDayWeather(
    val dt: Long,
    val main: MainWeatherInfo,
    val weather: List<WeatherDefinition>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
) : Serializable