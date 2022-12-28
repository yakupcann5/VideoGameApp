package com.yakupcan.videogameapp.domain.repository

import com.yakupcan.videogameapp.data.model.Results

interface AllGameRepository {
    suspend fun getGames(apiKey: String): ArrayList<Results>
}