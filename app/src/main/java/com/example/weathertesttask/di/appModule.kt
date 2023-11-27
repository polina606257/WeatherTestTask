package com.example.weathertesttask.di

import com.example.weathertesttask.ui.homePage.FiveDaysWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { FiveDaysWeatherViewModel(getFiveDaysForecastUseCase = get()) }
}