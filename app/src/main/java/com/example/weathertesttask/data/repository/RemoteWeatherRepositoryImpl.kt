package com.example.weathertesttask.data.repository

import com.example.weathertesttask.data.remote.WeatherApiDataSource
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class RemoteWeatherRepositoryImpl(private val weatherApiDataSource: WeatherApiDataSource) : RemoteWeatherRepository {
    override suspend fun getFiveDaysForecast(lat: Double, lon: Double): List<ModifiedWeatherEntity> =
        weatherApiDataSource.getFiveDaysForecast(lat, lon)
}