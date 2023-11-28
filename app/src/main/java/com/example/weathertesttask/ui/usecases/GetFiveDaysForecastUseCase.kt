package com.example.weathertesttask.ui.usecases

import com.example.weathertesttask.data.DataResult
import com.example.weathertesttask.data.repository.RemoteWeatherRepository
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class GetFiveDaysForecastUseCase(private val repository: RemoteWeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): DataResult<List<ModifiedWeatherEntity>> {
        return try {
            val fiveDaysForecast = repository.getFiveDaysForecast(lat, lon)
            DataResult.Success(fiveDaysForecast)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }
}