package com.yakupcan.videogameapp.data.repository

import com.yakupcan.videogameapp.data.Service
import com.yakupcan.videogameapp.data.model.Results
import com.yakupcan.videogameapp.domain.repository.AllGameRepository
import javax.inject.Inject

class AllGameRepositoryImpl @Inject constructor(private val service: Service) : AllGameRepository {
    override suspend fun getGames(apiKey: String): ArrayList<Results> {
        return service.getGames(apiKey).results
    }
}
