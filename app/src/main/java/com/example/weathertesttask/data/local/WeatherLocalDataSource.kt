package com.example.weathertesttask.data.local

import com.example.weathertesttask.domain.ModifiedWeatherEntity

class WeatherLocalDataSource(weatherDao: WeatherDao) : WeatherDao {
    private val localService: WeatherDao by lazy {
        weatherDao
    }

    override fun getAll(): List<ModifiedWeatherEntity> {
        return localService.getAll()
    }

    override fun insert(dayWeather: ModifiedWeatherEntity) {
        localService.insert(dayWeather)
    }
}