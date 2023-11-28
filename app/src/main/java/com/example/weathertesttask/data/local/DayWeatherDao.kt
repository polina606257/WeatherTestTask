package com.example.weathertesttask.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.example.weathertesttask.domain.WeatherEntityForRoom

@Dao
interface DayWeatherDao {
    @Query("SELECT * FROM weatherEntityForRoom")
    fun getAll(): List<WeatherEntityForRoom>

    @Insert
    fun insert(dayWeather: WeatherEntityForRoom)

}