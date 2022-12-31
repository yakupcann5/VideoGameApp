package com.yakupcan.videogameapp.domain.model

import androidx.room.Entity

@Entity(tableName = "game", primaryKeys = ["id"])
data class Game(
    var id: Int = 0,
    val name: String = "",
    val released: String = "",
    val backgroundImage: String = "",
    val rating: String = "",
    val ratingTop: Int = 0
) : java.io.Serializable
