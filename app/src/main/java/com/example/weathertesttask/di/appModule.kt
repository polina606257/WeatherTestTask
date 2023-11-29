package com.example.weathertesttask.di

import com.example.weathertesttask.data.remote.ConnectionDetector
import com.example.weathertesttask.ui.homePage.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        ForecastViewModel(
            getForecastFromApiUseCase = get(),
            getForecastFromDatabaseUseCase = get(),
            saveDayWeatherToDatabaseUseCase = get(),
            connectionDetector = ConnectionDetector(get())
        )
    }
}