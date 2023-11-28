package com.example.weathertesttask.data.local

import android.content.Context
import androidx.room.Room

object DatabaseFactory {
    fun provideDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-forecast"
        ).build()
    }
}