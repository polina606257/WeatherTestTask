package com.example.weathertesttask.domain

data class WeatherResponse(
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<HourlyDayWeather>,
    val city: City,
)