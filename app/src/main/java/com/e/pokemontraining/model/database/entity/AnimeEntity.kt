package com.e.pokemontraining.model.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animes")
data class AnimeEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    var name: String
)