package com.e.pokemontraining.ui.favorites

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.di.animeRepository
import com.e.pokemontraining.di.databaseRepository
import com.e.pokemontraining.model.api.response.AnimeDetail
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesViewModel constructor(val animerepository: animeRepository,val databaseRepository: databaseRepository) : BaseViewModel() {
    fun listanime(recyclerView: RecyclerView) {
        var list = databaseRepository.getanimes()
        var animelist: MutableList<AnimeDetail> = mutableListOf()
        for (anime in list) {
           // var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
            animerepository.getanime(anime.name)?.enqueue(object : Callback<AnimeDetail> {
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