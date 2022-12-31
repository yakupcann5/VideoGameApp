package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.GameDAO
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(private val dao: GameDAO) {
    operator fun invoke(id: List<Int>) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getGameByIds(id)))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        }
    }
}