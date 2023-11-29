package com.example.weathertesttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathertesttask.domain.ModifiedWeatherEntity

@Database(entities = [ModifiedWeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dayWeatherDao(): WeatherDao
}