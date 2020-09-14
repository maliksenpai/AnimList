package com.e.pokemontraining.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createnetwork("https://api.jikan.moe/v3/") }
}

fun createnetwork(url:String) : Retrofit = getretrofit(url)

fun getretrofit(url:String):Retrofit = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()