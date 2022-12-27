package com.yakupcan.videogameapp.domain.model

data class Game(
    val id: Int = 0,
    val name: String = "",
    val released: String = "",
    val backgroundImage: String = "",
    val rating: Double = 0.0,
    val ratingTop: Int = 0
) : java.io.Serializable
