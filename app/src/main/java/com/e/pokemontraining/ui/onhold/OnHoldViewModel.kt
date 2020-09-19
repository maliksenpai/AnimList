package com.e.pokemontraining.ui.onhold

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.di.animeRepository
import com.e.pokemontraining.di.databaseRepository
import com.e.pokemontraining.model.api.response.UserAnime
import com.e.pokemontraining.model.api.response.UserAnimeList
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response

class OnHoldViewModel constructor(val animerepository: animeRepository,val databaseRepository: databaseRepository) : BaseViewModel() {
    val TYPE: String = "onhold"
    fun listanime(nickname: String, recyclerView: RecyclerView) {
        var list: MutableList<UserAnime> = emptyList<UserAnime>().toMutableList()
        var listpost = animerepository.getuseranimes(nickname, TYPE)
        listpost?.enqueue(object : retrofit2.Callback<UserAnimeList> {
            override fun onFailure(call: Call<UserAnimeList>, t: Throwable) {
                Log.d("gelenhata", t.message)
            }

            override fun onResponse(call: Call<UserAnimeList>, response: Response<UserAnimeList>) {
                Log.d("gelen", response.toString())
                var animes = response.body()?.animes
                if (animes != null) {
                    for (anime in animes) {
                        list.add(anime)
                    }
                    recyclerView.adapter = OnHoldAdapter(list,databaseRepository)
                }
            }

        })
    }
}