package com.e.pokemontraining.ui.watching

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityWatchingBinding
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.UserAnime
import com.e.pokemontraining.model.api.response.UserAnimeList
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.ui.nickname.NicknameActivity
import com.e.pokemontraining.utils.Nickname
import retrofit2.Call
import retrofit2.Response

class WatchingActivity : BaseActivity<ActivityWatchingBinding,WatchingViewModel>(WatchingViewModel::class.java) {
    val TYPE:String="watching"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("gelen","alo")
        init()
    }

    override fun getLayout(): Int {
        return R.layout.activity_watching
    }

    override fun init() {
        checknickname()
        initdesign()
    }

    override fun initviewmodel() {
        binding.modelView=viewModel
    }

    fun initdesign(){
        supportActionBar?.title="Watching Animes"
    }

    fun checknickname(){
        if(Nickname().getnickname(this)?.isEmpty()!!){
            startActivity(Intent(this,NicknameActivity::class.java))
        }else{
            listanime(Nickname().getnickname(this)!!)
        }
    }

    fun listanime(nickname:String){
        var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var list:MutableList<UserAnime> = emptyList<UserAnime>().toMutableList()
        var recyclerView = findViewById<RecyclerView>(R.id.listanime)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WatchingAdapter(list)
        var listpost = postservice?.getuseranimes(nickname,TYPE)
        listpost?.enqueue(object : retrofit2.Callback<UserAnimeList>{
            override fun onFailure(call: Call<UserAnimeList>, t: Throwable) {
                Log.d("gelenhata",t.message)
            }

            override fun onResponse(call: Call<UserAnimeList>, response: Response<UserAnimeList>) {
                Log.d("gelen",response.toString())
                var animes = response.body()?.animes
                if (animes != null) {
                    for(anime in animes){
                        list.add(anime)
                    }
                    recyclerView.adapter = WatchingAdapter(list)
                }
            }

        })
    }
}