package com.example.weathertesttask.data.repository

import com.example.weathertesttask.data.local.WeatherLocalDataSource
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class LocalWeatherRepositoryImpl(val weatherLocalDataSource: WeatherLocalDataSource) : LocalWeatherRepository {
    override suspend fun getAll(): List<ModifiedWeatherEntity> {
        return weatherLocalDataSource.getAll()
    }

    override suspend fun insert(dayWeather: ModifiedWeatherEntity) {
        weatherLocalDataSource.insert(dayWeather)
    }
}