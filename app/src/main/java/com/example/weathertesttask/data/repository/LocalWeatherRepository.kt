package com.example.weathertesttask.data.repository

import com.example.weathertesttask.domain.WeatherEntityForRoom

interface LocalWeatherRepository {
    suspend fun getAll(): List<WeatherEntityForRoom>
    suspend fun insert(dayWeather: WeatherEntityForRoom)
}