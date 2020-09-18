package com.e.pokemontraining.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.model.database.entity.AnimeEntity

@Database(entities = arrayOf(AnimeEntity::class), version = 1)
abstract class AnimDatabase : RoomDatabase() {

    abstract fun dao(): AnimeDao

    companion object {
        private var instance: AnimDatabase? = null
        fun getinstance(context: Context): AnimDatabase =
            instance ?: synchronized(this) {
                instance ?: build(context).also { instance = it }
            }

        fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AnimDatabase::class.java, "animes")
                .allowMainThreadQueries().build()
    }
}