package com.yakupcan.videogameapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yakupcan.videogameapp.domain.model.Game

@Dao
interface GameDAO {
    @Insert
    suspend fun insertAllGame(vararg game: Game): List<Long>

    @Query("SELECT * FROM game")
    suspend fun getAllGame(): List<Game>

    @Query("DELETE FROM game")
    suspend fun deleteAllGame()

    @Query("SELECT * FROM game WHERE name LIKE :searchQuery")
    suspend fun getContentsBySearchQuery(searchQuery: String): List<Game>

    @Query("SELECT * FROM game WHERE id IN (:ids)")
    suspend fun getGameByIds(ids: List<Int>): List<Game>
}