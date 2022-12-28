package com.yakupcan.videogameapp.domain.repository

import com.yakupcan.videogameapp.data.model.SingleGameResponse

interface SingleGameRepository {
    suspend fun getSingleGame(id: Int, apiKey: String): SingleGameResponse
}