package com.e.pokemontraining

import android.app.Application

class App : Application() {
    var instance: App? = null

    override fun onCreate() {
        super.onCreate()
        instance=this
    }

    fun getApp(): App {
        if(instance==null){
            instance=this
        }
        return instance as App
    }
}