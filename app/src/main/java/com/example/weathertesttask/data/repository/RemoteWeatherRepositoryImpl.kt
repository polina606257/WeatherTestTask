package com.example.weathertesttask.data.repository

import com.example.weathertesttask.data.remote.WeatherDataSource
import com.example.weathertesttask.domain.WeatherResponse

class RemoteWeatherRepositoryImpl(private val weatherDataSource: WeatherDataSource) : RemoteWeatherRepository {
    override suspend fun getFiveDaysForecast(lat: Double, lon: Double): WeatherResponse =
        weatherDataSource.getFiveDaysForecast(lat, lon)
}