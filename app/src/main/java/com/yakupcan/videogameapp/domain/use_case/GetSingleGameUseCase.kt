package com.yakupcan.videogameapp.domain.use_case

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.domain.repository.SingleGameRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSingleGameUseCase @Inject constructor(
    private val singleGameRepository: SingleGameRepository
) {
    operator fun invoke(id: Int) = flow {
        try {
            emit(RequestState.Loading())
            val singleGame = singleGameRepository.getSingleGame(id, Constants.API_KEY)
            emit(RequestState.Success(singleGame))
        } catch (e: Exception) {
            emit(RequestState.Error(e))
        } catch (e: IOException) {
            emit(RequestState.Error(e))
        }
    }
}