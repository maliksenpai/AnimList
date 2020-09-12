package com.e.pokemontraining.ui.detail

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.AnimeDetail
import com.e.pokemontraining.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : BaseViewModel() {
    fun loadpage(image: ImageView, synopsis: TextView, title: TextView, id: String) {
        var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var detailpost = postservice?.getanime(id)
        detailpost?.enqueue(object : Callback<AnimeDetail> {
            override fun onFailure(call: Call<AnimeDetail>, t: Throwable) {
                Log.d("gelen", t.message)
            }

            override fun onResponse(call: Call<AnimeDetail>, response: Response<AnimeDetail>) {
                Glide.with(image.rootView).load(response.body()?.image).into(image)
                synopsis.setText(response.body()?.synopsis)
                title.setText(response.body()?.title)
            }

        })
    }
}