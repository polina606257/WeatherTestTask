package com.example.weathertesttask.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
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