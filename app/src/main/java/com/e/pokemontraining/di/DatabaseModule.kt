package com.e.pokemontraining.di

import com.e.pokemontraining.model.database.AnimDatabase
import com.e.pokemontraining.model.database.entity.AnimeEntity
import org.koin.dsl.module

val DatabaseModule = module {
    single { AnimDatabase.getinstance(get()) }
    single { get<AnimDatabase>().dao() }
    factory { databaseRepository(get()) }

}
class databaseRepository(private val database:AnimDatabase){
    fun checkfavorite(id:String) {
        if(database.dao().checkanime(id).name==null){
            database.dao().addanime(AnimeEntity(id))
        }else{
            database.dao().deleteanime(AnimeEntity(id))
        }
    }
    fun getanimes():List<AnimeEntity>{
        return database.dao().getfavoriteanimes()
    }
}