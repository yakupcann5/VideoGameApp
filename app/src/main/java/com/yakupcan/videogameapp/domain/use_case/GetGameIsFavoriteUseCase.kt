package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.FavGameDAO
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGameIsFavoriteUseCase@Inject constructor(private val dao: FavGameDAO) {
    operator fun invoke(id: Int) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getFavGameById(id)))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        }
    }
}