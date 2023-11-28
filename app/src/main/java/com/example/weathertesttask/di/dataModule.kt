package com.example.weathertesttask.di

import androidx.room.RoomDatabase
import com.example.weathertesttask.data.local.AppDatabase
import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideApiService
import com.example.weathertesttask.data.remote.ApiRetrofitFactory.provideRetrofit
import com.example.weathertesttask.data.remote.WeatherApiService
import com.example.weathertesttask.data.remote.WeatherDataSource
import com.example.weathertesttask.data.repository.RemoteWeatherRepository
import com.example.weathertesttask.data.repository.RemoteWeatherRepositoryImpl
import com.example.weathertesttask.data.local.DatabaseFactory.provideDatabase
import com.example.weathertesttask.data.local.DayWeatherDao
import com.example.weathertesttask.data.local.WeatherLocalDataSource
import com.example.weathertesttask.data.repository.LocalWeatherRepository
import com.example.weathertesttask.data.repository.LocalWeatherRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    factory<WeatherApiService> { provideApiService(retrofit = get()) }
    single<Retrofit> { provideRetrofit() }

    factory<WeatherDataSource> { WeatherDataSource(weatherApiService = get()) }
    single<RemoteWeatherRepository> { RemoteWeatherRepositoryImpl(weatherDataSource = get()) }

    single<AppDatabase> { provideDatabase(context = get()) }
    single<DayWeatherDao> { get<AppDatabase>().dayWeatherDao() }
    factory<WeatherLocalDataSource> { WeatherLocalDataSource(dayWeatherDao = get()) }

    single<LocalWeatherRepository> { LocalWeatherRepositoryImpl(weatherLocalDataSource = get()) }

}