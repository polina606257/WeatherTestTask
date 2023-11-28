package com.example.weathertesttask.utils

import com.example.weathertesttask.domain.HourlyDayWeather
import com.example.weathertesttask.domain.ModifiedWeatherEntity

fun createWeatherEntityForRoom(dayWeather: HourlyDayWeather): ModifiedWeatherEntity {
    return ModifiedWeatherEntity(
        dayWeather.dt,
        dayWeather.dtTxt,
        dayWeather.clouds.all,
        dayWeather.main.humidity,
        dayWeather.main.pressure,
        dayWeather.main.tempMin,
        dayWeather.main.tempMax,
        dayWeather.wind.speed,
        dayWeather.main.feelsLike,
        dayWeather.pop,
        dayWeather.sys.pod)
}