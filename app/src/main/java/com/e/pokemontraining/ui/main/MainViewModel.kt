package com.e.pokemontraining.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.Anime
import com.e.pokemontraining.model.api.response.Year
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


public class MainViewModel constructor(private val retrofit : Retrofit,val dao: AnimeDao) : BaseViewModel() {


    var retrofitt : Retrofit? = null

    public fun lol(){
        Log.d("gelen","asd")
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
        //var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        Log.d("gelen",retrofitt.toString()+"ss")
        var postservice = retrofit.create(AnimeApi.AnimeApiInterface::class.java)
        var list: MutableList<Anime> = emptyList<Anime>().toMutableList()
        //recyclerView.adapter = MainAdapter(list)
        var listpost = postservice?.getlist(season)
        listpost?.enqueue(object : Callback<Year> {
            override fun onFailure(call: Call<Year>, t: Throwable) {
                Log.d("gelenhata", t.message)
            }

            override fun onResponse(call: Call<Year>, response: Response<Year>) {
                var animes = response.body()?.listanime
                if (animes != null) {
                    for (anime in animes) {
                        list.add(anime)
                    }
                    Log.d("gelen", list[0].title)
                    recyclerView.adapter = MainAdapter(list,dao)
                }
            }
        })
    }


}