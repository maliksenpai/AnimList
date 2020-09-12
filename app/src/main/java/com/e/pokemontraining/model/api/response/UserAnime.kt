package com.e.pokemontraining.model.api.response

import com.google.gson.annotations.SerializedName

data class UserAnime(
    @SerializedName("mal_id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("image_url")
    var image: String,
    @SerializedName("score")
    var score: String,
    @SerializedName("watched_episodes")
    var watchedepisode: String,
    @SerializedName("total_episodes")
    var totalepisode: String
)