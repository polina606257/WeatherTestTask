package com.example.weathertesttask.di

import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideApiService
import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideRetrofit
import com.example.weathertesttask.data.remote.WeatherApiService
import com.example.weathertesttask.data.remote.WeatherDataSource
import com.example.weathertesttask.data.repository.WeatherRepository
import com.example.weathertesttask.data.repository.WeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    factory<WeatherApiService> { provideApiService(retrofit = get()) }
    single<Retrofit> { provideRetrofit() }

    factory<WeatherDataSource> { WeatherDataSource(weatherApiService = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(weatherDataSource = get()) }

}