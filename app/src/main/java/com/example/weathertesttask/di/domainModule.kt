package com.example.weathertesttask.di

import com.example.weathertesttask.ui.usecases.GetForecastFromApiUseCase
import com.example.weathertesttask.ui.usecases.GetForecastFromDatabaseUseCase
import com.example.weathertesttask.ui.usecases.SaveDayWeatherToDatabaseUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetForecastFromApiUseCase> { GetForecastFromApiUseCase(repository = get()) }
    factory<GetForecastFromDatabaseUseCase> {  GetForecastFromDatabaseUseCase( repository = get()) }
    factory<SaveDayWeatherToDatabaseUseCase> { SaveDayWeatherToDatabaseUseCase(repository = get()) }
}