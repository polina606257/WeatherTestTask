package com.example.weathertesttask.ui.usecases

import com.example.weathertesttask.data.DataResult
import com.example.weathertesttask.data.repository.WeatherRepository
import com.example.weathertesttask.domain.WeatherResponse

class GetFiveDaysForecastUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): DataResult<WeatherResponse> {
        return try {
            val fiveDaysForecast = repository.getFiveDaysForecast(lat, lon)
            DataResult.Success(fiveDaysForecast)
        } catch (e: Exception) {
            DataResult.Error(e.message)
        }
    }
}