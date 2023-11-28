package com.example.weathertesttask.data.repository

import com.example.weathertesttask.domain.WeatherResponse

interface RemoteWeatherRepository {
    suspend fun getFiveDaysForecast(lat: Double, lon: Double): WeatherResponse
}