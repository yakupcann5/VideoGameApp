package com.yakupcan.videogameapp.domain.model

import androidx.room.Entity

@Entity(tableName = "games", primaryKeys = ["id"])
data class GameModelItem(
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: Double,
    val ratingTop: Int
)
