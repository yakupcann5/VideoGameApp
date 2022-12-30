package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.FavGameDAO
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(private val dao: FavGameDAO) {
    operator fun invoke() = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getAllFavGame()))

        }catch (e: Exception) {

        }
        emit(dao.getAllFavGame())
    }
}
