package com.example.weathertesttask.di

import com.example.weathertesttask.data.local.AppDatabase
import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideApiService
import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideRetrofit
import com.example.weathertesttask.data.remote.WeatherApiService
import com.example.weathertesttask.data.remote.WeatherApiDataSource
import com.example.weathertesttask.data.repository.RemoteWeatherRepository
import com.example.weathertesttask.data.repository.RemoteWeatherRepositoryImpl
import com.example.weathertesttask.data.local.DatabaseFactory.provideDatabase
import com.example.weathertesttask.data.local.WeatherDao
import com.example.weathertesttask.data.repository.LocalWeatherRepository
import com.example.weathertesttask.data.repository.LocalWeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    factory<WeatherApiService> { provideApiService(retrofit = get()) }
    single<Retrofit> { provideRetrofit() }

    factory<WeatherApiDataSource> { WeatherApiDataSource(weatherApiService = get()) }
    single<RemoteWeatherRepository> { RemoteWeatherRepositoryImpl(weatherApiDataSource = get()) }

    single<AppDatabase> { provideDatabase(context = get()) }
    single<WeatherDao> { get<AppDatabase>().dayWeatherDao() }
    factory<com.example.weathertesttask.data.local.WeatherLocalDataSource> { com.example.weathertesttask.data.local.WeatherLocalDataSource(weatherDao = get()) }

    single<LocalWeatherRepository> { LocalWeatherRepositoryImpl(weatherLocalDataSource = get()) }

}