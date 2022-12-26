package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke() = flow {
        try {
            emit(RequestState.Loading())
            val games = gameRepository.getGames(Constants.API_KEY)
            emit(RequestState.Success(games))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        } catch (e: IOException) {
            emit(RequestState.Error(e))
        }
    }
}