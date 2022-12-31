package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.GameDAO
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchGameUseCase @Inject constructor(private val dao: GameDAO) {
    operator fun invoke(query: String) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getContentsBySearchQuery(query)))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        }
    }
}
