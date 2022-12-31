package com.yakupcan.videogameapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakupcan.videogameapp.db.dao.FavGameDAO
import com.yakupcan.videogameapp.db.dao.GameDAO
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import com.yakupcan.videogameapp.domain.model.Game

@Database(
    version = 1,
    entities = [
        Game::class,
        FavoriteGameEntities::class
    ],
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun getGameDao(): GameDAO
    abstract fun getFavGameDao(): FavGameDAO
}