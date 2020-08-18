package com.e.pokemontraining.model.api.response

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("title")
    var title:String,
    @SerializedName("image_url")
    var image:String,
    @SerializedName("episodes")
    var episodes:String,
    @SerializedName("score")
    var score:String,
    @SerializedName("mal_id")
    var id:String
)