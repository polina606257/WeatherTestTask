package com.example.weathertesttask.data.remote

import com.example.weathertesttask.domain.WeatherResponse

class WeatherDataSource(weatherApiService: WeatherApiService) {

    private val remoteService: WeatherApiService by lazy {
        weatherApiService
    }

    suspend fun getFiveDaysForecast(lat: Double, lon: Double): WeatherResponse {
        return remoteService.getFiveDaysForecast(lat, lon)
    }
}