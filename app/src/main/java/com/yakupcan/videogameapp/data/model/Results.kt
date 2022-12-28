package com.yakupcan.videogameapp.data.model

import com.google.gson.annotations.SerializedName

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