package com.e.pokemontraining.di

import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.ui.completed.CompletedViewModel
import com.e.pokemontraining.ui.detail.DetailViewModel
import com.e.pokemontraining.ui.favorites.FavoritesViewModel
import com.e.pokemontraining.ui.main.MainViewModel
import com.e.pokemontraining.ui.nickname.NicknameViewModel
import com.e.pokemontraining.ui.onhold.OnHoldViewModel
import com.e.pokemontraining.ui.watching.WatchingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

public val ApiModule = module {
    single { createnetwork("https://api.jikan.moe/v3/") }
    factory { animeRepository(get()) }
    viewModel { MainViewModel(get(),get()) }
    viewModel { CompletedViewModel(get(),get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { NicknameViewModel() }
    viewModel { FavoritesViewModel(get(),get()) }
    viewModel { OnHoldViewModel(get(),get()) }
    viewModel { WatchingViewModel(get(),get()) }
}

class animeRepository(private val animeApi: AnimeApi.AnimeApiInterface){
    fun getlist(day:String) = animeApi.getlist(day)
    fun getanime(id:String) = animeApi.getanime(id)
    fun getuseranimes(id:String,type:String) = animeApi.getuseranimes(id,type)
}