package com.example.weathertesttask.data.local

import com.example.weathertesttask.domain.ModifiedWeatherEntity

class WeatherLocalDataSource(dayWeatherDao: DayWeatherDao) : DayWeatherDao {
    private val localService: DayWeatherDao by lazy {
        dayWeatherDao
    }

    override fun getAll(): List<ModifiedWeatherEntity> {
        return localService.getAll()
    }

    override fun insert(dayWeather: ModifiedWeatherEntity) {
        localService.insert(dayWeather)
    }
}