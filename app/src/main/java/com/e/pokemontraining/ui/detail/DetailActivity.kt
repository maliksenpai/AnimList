package com.e.pokemontraining.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.e.pokemontraining.R
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.AnimeDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var id = intent.getStringExtra("id")
        init(id)
    }

    fun init(id:String){
        loadpage(id)
    }

    fun loadpage(id:String){
        var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var image = findViewById<ImageView>(R.id.detailimage)
        var synopsis = findViewById<TextView>(R.id.detailsynopsis)
        var title = findViewById<TextView>(R.id.detailtitle)
        var detailpost = postservice?.getanime(id)
        detailpost?.enqueue(object:Callback<AnimeDetail>{
            override fun onFailure(call: Call<AnimeDetail>, t: Throwable) {
                Log.d("gelen",t.message)
            }

            override fun onResponse(call: Call<AnimeDetail>, response: Response<AnimeDetail>) {
                Glide.with(this@DetailActivity).load(response.body()?.image).into(image)
                synopsis.setText(response.body()?.synopsis)
                title.setText(response.body()?.title)
            }

        })
    }
}