package com.yakupcan.videogameapp.data.model

import com.google.gson.annotations.SerializedName
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities

class SingleGameResponse(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("name_original")
    var nameOriginal: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("metacritic")
    var metacritic: Int? = null,
    @SerializedName("released")
    var released: String? = null,
    @SerializedName("tba")
    var tba: Boolean? = null,
    @SerializedName("updated")
    var updated: String? = null,
    @SerializedName("background_image")
    var backgroundImage: String? = null,
    @SerializedName("background_image_additional")
    var backgroundImageAdditional: String? = null,
    @SerializedName("website")
    var website: String? = null,
    @SerializedName("rating")
    var rating: Double? = null,
    @SerializedName("rating_top")
    var ratingTop: Int? = null,
    @SerializedName("description_raw")
    var descriptionRaw: String? = null
) {
    fun toFavoriteGameEntities(gameId: Int?): FavoriteGameEntities {
        return FavoriteGameEntities(
            id = gameId!!,
            time = System.currentTimeMillis(),
            isFavorite = true
        )
    }
}