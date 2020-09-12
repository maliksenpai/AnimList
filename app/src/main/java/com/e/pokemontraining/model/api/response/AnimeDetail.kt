package com.e.pokemontraining.model.api.response

import com.google.gson.annotations.SerializedName

data class AnimeDetail(
    @SerializedName("image_url")
    var image: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("synopsis")
    var synopsis: String,
    @SerializedName("mal_id")
    var id: String
)