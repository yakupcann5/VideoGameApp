package com.yakupcan.videogameapp.data.repository

import com.yakupcan.videogameapp.data.Service
import com.yakupcan.videogameapp.data.model.SingleGameResponse
import com.yakupcan.videogameapp.domain.repository.SingleGameRepository
import javax.inject.Inject

class SingleGameRepositoryImpl @Inject constructor(private val service: Service) :
    SingleGameRepository {
    override suspend fun getSingleGame(id: Int, apiKey: String): SingleGameResponse {
        return service.getDetailsOfGame(id.toString(), apiKey)
    }
}
