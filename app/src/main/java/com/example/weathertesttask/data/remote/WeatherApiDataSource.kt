package com.example.weathertesttask.data.remote

import com.example.weathertesttask.domain.ModifiedWeatherEntity
import com.example.weathertesttask.utils.createWeatherEntityForRoom

class WeatherApiDataSource(weatherApiService: WeatherApiService) {

    private val remoteService: WeatherApiService by lazy {
        weatherApiService
    }

    suspend fun getFiveDaysForecast(lat: Double, lon: Double): List<ModifiedWeatherEntity> {
        val responseData = remoteService.getFiveDaysForecast(lat, lon)
        val listOfModifiedWeather: MutableList<ModifiedWeatherEntity> = mutableListOf()
        for (dayWeather in responseData.list) {
            listOfModifiedWeather.add(createWeatherEntityForRoom(dayWeather))
        }
        return listOfModifiedWeather.toList()
    }
}