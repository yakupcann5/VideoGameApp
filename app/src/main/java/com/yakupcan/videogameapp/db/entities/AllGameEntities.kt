package com.yakupcan.videogameapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yakupcan.videogameapp.domain.model.Game

@Entity(tableName = "game", primaryKeys = ["id"])
data class AllGameEntities (
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "released") val released: String = "",
    @ColumnInfo(name = "backgroundImage") val backgroundImage: String = "",
    @ColumnInfo(name = "rating") val rating: Double = 0.0,
    @ColumnInfo(name = "ratingTop") val ratingTop: Int = 0,
)
fun AllGameEntities.toGame(): Game {
    return Game(
        id = id,
        name = name,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating.toString(),
        ratingTop = ratingTop,
    )
}
