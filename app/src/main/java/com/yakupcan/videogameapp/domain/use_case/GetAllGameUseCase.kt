package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.dao.GameDAO
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllGameUseCase @Inject constructor(
    private val dao: GameDAO
) {
    operator fun invoke() = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(dao.getAllGame()))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        } catch (e: IOException) {
            emit(RequestState.Error(e))
        }
    }
}