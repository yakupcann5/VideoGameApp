package com.yakupcan.videogameapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakupcan.videogameapp.db.dao.GameDAO
import com.yakupcan.videogameapp.db.entities.AllGameEntities
import com.yakupcan.videogameapp.domain.model.Game

@Database(
    version = 1,
    entities = [
        AllGameEntities::class
    ],
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun getGameDao(): GameDAO
}