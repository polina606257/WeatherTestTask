package com.example.weathertesttask

import android.app.Application
import com.example.weathertesttask.di.appModule
import com.example.weathertesttask.di.dataModule
import com.example.weathertesttask.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}