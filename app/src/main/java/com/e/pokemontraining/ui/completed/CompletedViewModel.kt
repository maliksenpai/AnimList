package com.e.pokemontraining.ui.completed

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.UserAnime
import com.e.pokemontraining.model.api.response.UserAnimeList
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class CompletedViewModel constructor(val retrofit: Retrofit,val dao: AnimeDao) : BaseViewModel() {

    val TYPE: String = "completed"
    fun listanime(recyclerView: RecyclerView, nickname: String) {
        //var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var postservice = retrofit.create(AnimeApi.AnimeApiInterface::class.java)
        var list: MutableList<UserAnime> = emptyList<UserAnime>().toMutableList()
        recyclerView.adapter = CompletedAdapter(list,dao)
        var listpost = postservice?.getuseranimes(nickname, TYPE)
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
                    recyclerView.adapter = CompletedAdapter(list,dao)
                }
            }

        })
    }
}