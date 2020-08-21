package com.e.pokemontraining.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.pokemontraining.model.database.dao.AnimeDao
import com.e.pokemontraining.model.database.entity.AnimeEntity

@Database(entities = arrayOf(AnimeEntity::class) , version = 1)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun dao():AnimeDao
}