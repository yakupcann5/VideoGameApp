package com.yakupcan.videogameapp.domain.model

data class SingleGame(
    val id: Int = 0,
    val name: String = "",
    val released: String = "",
    val backgroundImage: String = "",
    val rating: Double = 0.0,
    var metacritic: Int? = 0,
    var descriptionRaw: String? = ""
)
