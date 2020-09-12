package com.e.pokemontraining.utils

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.e.pokemontraining.model.database.AnimeDatabase
import com.e.pokemontraining.model.database.entity.AnimeEntity

class Favorite() {
    fun checkfavorite(id: String, context: Context) {
        val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()
        // db.dao().nukedatabase()
        if (db.dao()?.checkanime(id)?.name == null) {
            addfavorite(id, context)
        } else {
            removefavorite(id, context)
        }
    }

    fun removefavorite(id: String, context: Context) {
        val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()
        db.dao().deleteanime(AnimeEntity(id))
    }

    fun addfavorite(id: String, context: Context) {
        val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()
        db.dao().addanime(AnimeEntity((id)))
        Log.d("gelen", db.dao().getfavoriteanimes().toString())
    }

    fun listanimes(context: Context): List<AnimeEntity> {
        val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()
        return db.dao().getfavoriteanimes()
    }

}