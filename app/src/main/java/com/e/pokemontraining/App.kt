package com.e.pokemontraining

import android.app.Application
import com.e.pokemontraining.di.networkModule
import com.e.pokemontraining.ui.main.mainmodule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            this@App
            modules(networkModule, mainmodule)
            androidLogger()
        }
    }
}