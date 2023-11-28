package com.example.weathertesttask.data.repository

import com.example.weathertesttask.data.local.WeatherLocalDataSource
import com.example.weathertesttask.domain.WeatherEntityForRoom

class LocalWeatherRepositoryImpl(val weatherLocalDataSource: WeatherLocalDataSource) : LocalWeatherRepository {
    override suspend fun getAll(): List<WeatherEntityForRoom> {
        return weatherLocalDataSource.getAll()
    }

    override suspend fun insert(dayWeather: WeatherEntityForRoom) {
        weatherLocalDataSource.insert(dayWeather)
    }
}