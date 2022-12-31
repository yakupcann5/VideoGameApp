package com.yakupcan.videogameapp.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.data.model.SingleGameResponse
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.domain.use_case.GetGameIsFavoriteUseCase
import com.yakupcan.videogameapp.domain.use_case.GetSingleGameUseCase
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val preferences: MyPreferences,
    private val useCase: GetSingleGameUseCase,
    private val database: Database,
    private val getGameIsFavoriteUseCase: GetGameIsFavoriteUseCase
) : ViewModel() {
    private val TAG = "DetailViewModel"
    val gameIsFavorited = MutableLiveData<Boolean>()
    private val _singleGame = MutableStateFlow<RequestState<SingleGameResponse>?>(null)
    var singleGame: MutableStateFlow<RequestState<SingleGameResponse>?> = _singleGame

    fun getSingleGame(id: Int) {
        useCase(id).onEach { result ->
            when (result) {
                is RequestState.Success -> {
                    _singleGame.value = result
                }
                is RequestState.Error -> {
                    Log.d(TAG, "getSingleGame: ${result.exception}")
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "getSingleGame: Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun gameIsFavorite(id: Int) {
        getGameIsFavoriteUseCase.invoke(id).onEach { result ->
            when (result) {
                is RequestState.Success -> {
                    if (result.data != null) {
                        gameIsFavorited.value = true
                    } else {
                        gameIsFavorited.value = false
                    }
                }
                is RequestState.Error -> {
                    Log.d(TAG, "gameIsFavorite: ${result.exception}")
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "gameIsFavorite: Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToFav(gameId: Int?) {
        viewModelScope.launch {
            database.getFavGameDao()
                .insertFavGame(singleGame.value?.data?.toFavoriteGameEntities(gameId)!!)
            Log.d(TAG, "addToFav: $gameId")
        }
    }

    fun deleteToFav(gameId: Int?) {
        viewModelScope.launch {
            database.getFavGameDao()
                .deleteFavGame(singleGame.value?.data?.toFavoriteGameEntities(gameId)!!)
            Log.d(TAG, "deleteToFav: $gameId")
        }
    }
}