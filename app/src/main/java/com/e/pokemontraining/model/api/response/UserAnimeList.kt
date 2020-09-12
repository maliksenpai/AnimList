package com.e.pokemontraining.model.api.response

import com.google.gson.annotations.SerializedName

data class UserAnimeList(
    @SerializedName("anime")
    var animes: List<UserAnime>
)