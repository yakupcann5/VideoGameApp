package com.yakupcan.videogameapp.db.entities

import androidx.room.Entity

@Entity(tableName = "favGame", primaryKeys = ["id"])
data class FavoriteGameEntities (
    val id: Int = 0,
    val time: Long = 0,
    val isFavorite: Boolean = false
)