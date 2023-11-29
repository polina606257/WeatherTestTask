package com.example.weathertesttask.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.example.weathertesttask.domain.ModifiedWeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM modifiedWeatherEntity")
    fun getAll(): List<ModifiedWeatherEntity>

    @Insert
    fun insert(dayWeather: ModifiedWeatherEntity)

}