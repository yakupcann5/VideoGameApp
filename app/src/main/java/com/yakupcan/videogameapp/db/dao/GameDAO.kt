package com.yakupcan.videogameapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yakupcan.videogameapp.db.entities.AllGameEntities
import com.yakupcan.videogameapp.domain.model.Game

@Dao
interface GameDAO {
    @Insert
    suspend fun insertAllGame(vararg game: AllGameEntities): List<Long>

    @Query("SELECT * FROM game")
    suspend fun getAllGame(): List<AllGameEntities>

    @Query("SELECT * FROM game WHERE id = :gameId")
    suspend fun getGame(gameId: Int): AllGameEntities

    @Query("DELETE FROM game")
    suspend fun deleteAllGame()

    @Query("DELETE FROM game WHERE id = :gameId")
    suspend fun deleteGame(gameId: Int)

    @Query("SELECT * FROM game WHERE name LIKE :searchQuery")
    suspend fun getContentsBySearchQuery(searchQuery: String): List<AllGameEntities>

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun getGameById(id: Int): AllGameEntities
}