package com.e.pokemontraining.model.api.response

import com.google.gson.annotations.SerializedName

data class Year(
    @SerializedName("anime")
    var listanime:List<Anime>
)