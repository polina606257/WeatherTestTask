package com.example.weathertesttask.data.remote

import com.example.weathertesttask.domain.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
 @GET("forecast")
 suspend fun getFiveDaysForecast(@Query("lat") lat: Double, @Query("lon") lon: Double) : WeatherResponse
}