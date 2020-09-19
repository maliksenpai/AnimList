package com.e.pokemontraining.di

import com.e.pokemontraining.model.api.AnimeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public fun createnetwork(url:String) : AnimeApi.AnimeApiInterface = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()
    .create(AnimeApi.AnimeApiInterface::class.java)
