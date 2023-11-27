package com.example.weathertesttask.di

import com.example.weathertesttask.ui.usecases.GetFiveDaysForecastUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetFiveDaysForecastUseCase> { GetFiveDaysForecastUseCase(repository = get()) }
}