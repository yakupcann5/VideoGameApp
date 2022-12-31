package com.yakupcan.videogameapp.data.model

import com.google.gson.annotations.SerializedName
import com.yakupcan.videogameapp.domain.model.Game

data class Results(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("released")
    var released: String? = null,
    @SerializedName("tba")
    var tba: Boolean? = null,
    @SerializedName("background_image")
    var backgroundImage: String? = null,
    @SerializedName("rating")
    var rating: Double? = null,
    @SerializedName("rating_top")
    var ratingTop: Int? = null
)
fun Results.toGame(): Game {
    return Game(
        id = id!!,
        name = name!!,
        released = released!!,
        backgroundImage = backgroundImage!!,
        rating = rating.toString(),
        ratingTop = ratingTop!!
    )
}