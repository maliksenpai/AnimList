package com.e.pokemontraining.ui.favorites

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.AnimeDetail
import com.e.pokemontraining.model.database.entity.AnimeEntity
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesViewModel : BaseViewModel() {
    fun listanime(recyclerView: RecyclerView, list: List<AnimeEntity>) {
        var animelist: MutableList<AnimeDetail> = mutableListOf()
        recyclerView.adapter = FavoritesAdapter(animelist)
        for (anime in list) {
            var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
            postservice?.getanime(anime.name)?.enqueue(object : Callback<AnimeDetail> {
                override fun onFailure(call: Call<AnimeDetail>, t: Throwable) {
                    Log.d("gelen", "hata")
                }

                override fun onResponse(call: Call<AnimeDetail>, response: Response<AnimeDetail>) {
                    animelist.add(
                        AnimeDetail(
                            response.body()?.image.toString(),
                            response.body()?.title.toString(),
                            response.body()?.synopsis.toString(),
                            response.body()?.id.toString()
                        )
                    )
                    recyclerView.adapter = FavoritesAdapter(animelist)
                }

            })
        }
    }
}