package com.yakupcan.videogameapp.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.yakupcan.videogameapp.db.dao.GameDAO

@Database(
    version = 1,
    entities = [

    ],
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun getGameDao(): GameDAO
}