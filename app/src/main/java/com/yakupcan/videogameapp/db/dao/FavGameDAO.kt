package com.yakupcan.videogameapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import org.jetbrains.annotations.NotNull

@Dao
interface FavGameDAO {
    @Insert
    suspend fun insertFavGame(vararg favGame: FavoriteGameEntities): List<Long>

    @Query("SELECT * FROM favGame")
    suspend fun getAllFavGame(): List<FavoriteGameEntities>

    @Query("SELECT * FROM favGame WHERE id = :favGameId")
    suspend fun getFavGame(favGameId: Int): FavoriteGameEntities

    @Delete
    suspend fun deleteFavGame(vararg favGame: FavoriteGameEntities)

    @Query("SELECT * FROM favGame WHERE isFavorite = :isFavorite")
    suspend fun getFavGameByFavorite(isFavorite: Boolean): List<FavoriteGameEntities>

    @Query("SELECT * FROM favGame WHERE id = :id")
    suspend fun getFavGameById(id: Int): List<FavoriteGameEntities>
}