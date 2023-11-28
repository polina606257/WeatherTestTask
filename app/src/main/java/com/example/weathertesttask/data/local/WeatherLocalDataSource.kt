package com.example.weathertesttask.data.local

import com.example.weathertesttask.domain.WeatherEntityForRoom

class WeatherLocalDataSource(dayWeatherDao: DayWeatherDao) : DayWeatherDao {
    private val localService: DayWeatherDao by lazy {
        dayWeatherDao
    }

    override fun getAll(): List<WeatherEntityForRoom> {
        return localService.getAll()
    }

    override fun insert(dayWeather: WeatherEntityForRoom) {
        localService.insert(dayWeather)
    }

}