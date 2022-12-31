package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.data.model.toGame
import com.yakupcan.videogameapp.domain.repository.AllGameRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val allGameRepository: AllGameRepository
) {
    operator fun invoke() = flow {
        try {
            emit(RequestState.Loading())
            val games = allGameRepository.getGames(Constants.API_KEY).map { it.toGame() }
            emit(RequestState.Success(games))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        }
    }
}