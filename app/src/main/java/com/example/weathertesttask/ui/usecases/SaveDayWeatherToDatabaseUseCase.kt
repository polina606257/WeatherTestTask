package com.example.weathertesttask.ui.usecases

import android.util.Log
import com.example.weathertesttask.data.repository.LocalWeatherRepository
import com.example.weathertesttask.domain.WeatherEntityForRoom

class SaveDayWeatherToDatabaseUseCase(val repository: LocalWeatherRepository) {
    suspend operator fun invoke(dayWeather: WeatherEntityForRoom) {
        try {
            repository.insert(dayWeather)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}