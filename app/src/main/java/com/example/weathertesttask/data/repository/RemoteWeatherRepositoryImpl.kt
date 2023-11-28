package com.example.weathertesttask.data.repository

import com.example.weathertesttask.data.remote.WeatherDataSource
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class RemoteWeatherRepositoryImpl(private val weatherDataSource: WeatherDataSource) : RemoteWeatherRepository {
    override suspend fun getFiveDaysForecast(lat: Double, lon: Double): List<ModifiedWeatherEntity> =
        weatherDataSource.getFiveDaysForecast(lat, lon)
}