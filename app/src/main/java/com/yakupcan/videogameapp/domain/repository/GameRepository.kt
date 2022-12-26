package com.yakupcan.videogameapp.domain.repository

import com.yakupcan.videogameapp.domain.model.Game

interface GameRepository {
    suspend fun getGames(apiKey: String): List<Game>
}