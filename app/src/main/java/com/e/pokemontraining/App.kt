package com.e.pokemontraining

import android.app.Application
import com.e.pokemontraining.di.ApiModule
import com.e.pokemontraining.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            this@App
            modules(listOf(ApiModule, DatabaseModule))
            androidContext(applicationContext)
            androidLogger()
        }
    }
}