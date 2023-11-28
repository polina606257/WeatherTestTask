package com.example.weathertesttask.ui.usecases

import android.util.Log
import com.example.weathertesttask.data.repository.LocalWeatherRepository
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class SaveDayWeatherToDatabaseUseCase(val repository: LocalWeatherRepository) {
    suspend operator fun invoke(dayWeather: ModifiedWeatherEntity) {
        try {
            repository.insert(dayWeather)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}