package com.e.pokemontraining.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityMainBinding
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.Anime
import com.e.pokemontraining.model.api.response.Year
import com.e.pokemontraining.ui.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(MainViewModel::class.java) {

    val DEFAULT_SEASON = "winter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init(){
        initviewmodel()
        listanime(DEFAULT_SEASON)
    }

    override fun initviewmodel(){
        binding.viewmodel = viewModel
        var i=1
        val seasonobserver = Observer<String> {
            listanime(it)
        }
        viewModel.radiobutton.observe(this,seasonobserver)
    }



    fun listanime(season:String){
        var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var list:MutableList<Anime> = emptyList<Anime>().toMutableList()
        var recyclerView = findViewById<RecyclerView>(R.id.listanime)
        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = MainAdapter(list)
        var listpost = postservice?.getlist(season)
        listpost?.enqueue(object : Callback<Year>{
            override fun onFailure(call: Call<Year>, t: Throwable) {
                Log.d("gelenhata",t.message)
            }

            override fun onResponse(call: Call<Year>, response: Response<Year>) {
                var animes = response.body()?.listanime
                if (animes != null) {
                    for(anime in animes){
                        list.add(anime)
                    }
                    recyclerView.adapter = MainAdapter(list)
                }
            }
        })
    }
    

}
