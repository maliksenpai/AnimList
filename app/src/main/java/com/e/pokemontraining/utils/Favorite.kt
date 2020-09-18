package com.e.pokemontraining.utils

import android.content.Context
import androidx.room.Room
import com.e.pokemontraining.model.database.AnimeDatabase
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.model.database.entity.AnimeEntity

class Favorite {

    fun checkfavorite(id: String, dao:AnimeDao) {
        /*val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()*/
        // db.dao().nukedatabase()
        if (dao.checkanime(id)?.name == null) {
            addfavorite(id, dao)
        } else {
            removefavorite(id, dao)
        }
    }

    fun removefavorite(id: String, dao:AnimeDao) {
        /*val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()*/
        dao.deleteanime(AnimeEntity(id))
    }

    fun addfavorite(id: String, dao:AnimeDao) {
       /* val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()*/
        dao.addanime(AnimeEntity((id)))
    }

    fun listanimes(context: Context): List<AnimeEntity> {
        val db = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java, "animes"
        ).allowMainThreadQueries().build()
        return db.dao().getfavoriteanimes()
    }



}