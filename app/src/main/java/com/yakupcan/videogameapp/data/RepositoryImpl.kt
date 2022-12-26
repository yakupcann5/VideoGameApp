package com.yakupcan.videogameapp.data

import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.repository.GameRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: Service) : GameRepository {
    override suspend fun getGames(apiKey: String): ArrayList<Game> {
        return service.getGames(apiKey).results
    }
}
