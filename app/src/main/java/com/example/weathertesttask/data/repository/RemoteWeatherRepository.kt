package com.example.weathertesttask.data.repository

import com.example.weathertesttask.domain.ModifiedWeatherEntity

interface RemoteWeatherRepository {
    suspend fun getFiveDaysForecast(lat: Double, lon: Double): List<ModifiedWeatherEntity>
}