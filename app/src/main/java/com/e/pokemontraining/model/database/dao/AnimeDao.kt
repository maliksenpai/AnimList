package com.e.pokemontraining.model.database.dao

import androidx.room.*
import com.e.pokemontraining.model.database.entity.AnimeEntity

@Dao
public interface AnimeDao {
    @Query("select * from animes")
    fun getfavoriteanimes(): List<AnimeEntity>

    @Query("select * from animes where name in (:animename)")
    fun checkanime(animename: String): AnimeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addanime(animename: AnimeEntity)

    @Delete
    fun deleteanime(animename: AnimeEntity)

    @Query("delete from animes")
    fun nukedatabase()
}