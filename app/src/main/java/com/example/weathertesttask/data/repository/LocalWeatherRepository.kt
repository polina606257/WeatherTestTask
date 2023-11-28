package com.example.weathertesttask.data.repository

import com.example.weathertesttask.domain.ModifiedWeatherEntity

interface LocalWeatherRepository {
    suspend fun getAll(): List<ModifiedWeatherEntity>
    suspend fun insert(dayWeather: ModifiedWeatherEntity)
}