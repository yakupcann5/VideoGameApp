package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.GameDAO
import com.yakupcan.videogameapp.db.entities.toGame
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DenemeUseCase @Inject constructor(private val dao: GameDAO) {
    operator fun invoke(id: Int) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getGameById(id).toGame()))
        } catch (e: Exception) {

        }
    }
}