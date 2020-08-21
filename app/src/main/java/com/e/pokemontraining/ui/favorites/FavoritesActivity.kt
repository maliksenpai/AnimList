package com.e.pokemontraining.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityFavoritesBinding
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.AnimeDetail
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.utils.Favorite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesActivity : BaseActivity<ActivityFavoritesBinding,FavoritesViewModel>(FavoritesViewModel::class.java) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
       return R.layout.activity_favorites
    }

    override fun init() {
        listanime()
        initdesign()
    }

    override fun initviewmodel() {

    }

    fun initdesign(){
        supportActionBar?.title="Favorite Animes"
    }

    fun listanime(){
        var animelist:MutableList<AnimeDetail> = mutableListOf()
        var recyclerView = findViewById<RecyclerView>(R.id.listanime)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FavoritesAdapter(animelist)
        var list = Favorite().listanimes(this)
        for(anime in list){
            var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
            postservice?.getanime(anime.name)?.enqueue(object : Callback<AnimeDetail>{
                override fun onFailure(call: Call<AnimeDetail>, t: Throwable) {
                    Log.d("gelen","hata")
                }

                override fun onResponse(call: Call<AnimeDetail>, response: Response<AnimeDetail>) {
                    animelist.add(AnimeDetail(
                            response.body()?.image.toString(),
                            response.body()?.title.toString(),
                            response.body()?.synopsis.toString(),
                            response.body()?.id.toString())
                    )
                    recyclerView.adapter = FavoritesAdapter(animelist)
                }

            })
        }
    }
}