package com.e.pokemontraining.model.api

import com.e.pokemontraining.model.api.response.AnimeDetail
import com.e.pokemontraining.model.api.response.UserAnime
import com.e.pokemontraining.model.api.response.UserAnimeList
import com.e.pokemontraining.model.api.response.Year
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

public class AnimeApi (){
    private var retrofit: Retrofit? =null
    private val url : String = "https://api.jikan.moe/v3/"
    fun build():Retrofit?{
        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .build()
            return retrofit
        }
        return retrofit
    }
    interface AnimeApiInterface{
        @GET("season/2019/{year}")
        fun getlist(@Path("year")day :String): Call<Year>
        @GET("anime/{id}")
        fun getanime(@Path("id")id:String):Call<AnimeDetail>
        @GET("user/{id}/animelist/{type}")
        fun getuseranimes(@Path("id")id:String,@Path("type")type:String):Call<UserAnimeList>
    }
}