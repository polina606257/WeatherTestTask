package com.example.weathertesttask.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ModifiedWeatherEntity (
    @PrimaryKey
    val dt: Long,
    val dtTxt: String,
    val clouds: Long,
    val humidity: Long,
    val pressure: Long,
    val tempMin: Double,
    val tempMax: Double,
    val wind: Double,
    val feelsLike: Double,
    val pop: Double,
    val sys: String
) : Serializable