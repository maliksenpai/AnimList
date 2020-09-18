package com.e.pokemontraining.di

import com.e.pokemontraining.model.database.AnimDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    single { AnimDatabase.getinstance(androidApplication()) }
    single { get<AnimDatabase>().dao() }
}