package com.example.weathertesttask.di

import com.example.weathertesttask.ui.usecases.GetFiveDaysForecastUseCase
import com.example.weathertesttask.ui.usecases.GetForecastFromDatabaseUseCase
import com.example.weathertesttask.ui.usecases.SaveDayWeatherToDatabaseUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetFiveDaysForecastUseCase> { GetFiveDaysForecastUseCase(repository = get()) }
    factory<GetForecastFromDatabaseUseCase> {  GetForecastFromDatabaseUseCase( repository = get()) }
    factory<SaveDayWeatherToDatabaseUseCase> { SaveDayWeatherToDatabaseUseCase(repository = get()) }
}