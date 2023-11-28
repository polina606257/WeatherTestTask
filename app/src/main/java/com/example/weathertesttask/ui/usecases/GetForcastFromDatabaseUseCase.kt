package com.example.weathertesttask.ui.usecases

import com.example.weathertesttask.data.DataResult
import com.example.weathertesttask.data.repository.LocalWeatherRepository
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class GetForecastFromDatabaseUseCase(val repository: LocalWeatherRepository) {
    suspend operator fun invoke(): DataResult<List<ModifiedWeatherEntity>> {
        return try {
            val forecastFromDatabase = repository.getAll()
            DataResult.Success(forecastFromDatabase)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }
}