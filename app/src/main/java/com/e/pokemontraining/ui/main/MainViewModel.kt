package com.e.pokemontraining.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.di.animeRepository
import com.e.pokemontraining.di.databaseRepository
import com.e.pokemontraining.model.api.response.Anime
import com.e.pokemontraining.model.api.response.Year
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


public class MainViewModel constructor(private val animerepository: animeRepository, val databaseRepository: databaseRepository) :
    BaseViewModel() {


    var retrofitt: Retrofit? = null

    public fun lol() {
        Log.d("gelen", "asd")
    }

    var radiobutton = MutableLiveData<String>()

    fun winterradio() {
        changeradio(1)
    }

    fun springradio() {
        changeradio(2)
    }

    fun summerradio() {
        changeradio(3)
    }

    fun fallradio() {
        changeradio(4)
    }

    fun changeradio(season: Int): MutableLiveData<String> {
        when (season) {
            1 -> radiobutton.value = "winter"
            2 -> radiobutton.value = "spring"
            3 -> radiobutton.value = "summer"
            4 -> radiobutton.value = "fall"
        }
        return radiobutton
    }

    fun listanime(season: String, recyclerView: RecyclerView) {
        var list: MutableList<Anime> = emptyList<Anime>().toMutableList()
        var listpost = animerepository.getlist(season)
        listpost?.enqueue(object : Callback<Year> {
            override fun onFailure(call: retrofit2.Call<Year>, t: Throwable) {
                Log.d("gelenhata", t.message)
            }

            override fun onResponse(call: retrofit2.Call<Year>, response: Response<Year>) {
                var animes = response.body()?.listanime
                if (animes != null) {
                    for (anime in animes) {
                        list.add(anime)
                    }
                    recyclerView.adapter = MainAdapter(list,databaseRepository)
                }
            }
        })
    }


}