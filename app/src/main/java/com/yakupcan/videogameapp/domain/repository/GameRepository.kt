package com.yakupcan.videogameapp.domain.repository

import com.yakupcan.videogameapp.data.Results

interface GameRepository {
    suspend fun getGames(apiKey: String): ArrayList<Results>
}