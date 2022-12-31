package com.yakupcan.videogameapp.ui.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.use_case.GetGameByIdUseCase
import com.yakupcan.videogameapp.domain.use_case.GetFavoriteUseCase
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val preferences: MyPreferences,
    private val database: Database,
    private val favoriteUseCase: GetFavoriteUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase
) : ViewModel() {
    private val TAG = "FavoriteViewModel"
    val favGame = MutableLiveData<ArrayList<FavoriteGameEntities>>()
    val allFavGame = MutableLiveData<ArrayList<Game>>()
    val list = arrayListOf<Game>()

    fun setCurrentFragment(fragment: String) {
        preferences.setString("currentFragment", fragment)
    }

    fun getFavoriteGames() {
        favoriteUseCase.invoke().onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    result.data?.map { it.id }?.let { getGameById(it) }
                }
                is RequestState.Error -> {
                    Log.d(TAG, "getFavoriteGames: ${result.exception}")
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "getFavoriteGames: Loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getGameById(id: List<Int>) {
        getGameByIdUseCase.invoke(id).onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    allFavGame.value = result.data as ArrayList<Game>
                    Log.d(TAG, "getGameById: ${result.data}")
                }
                is RequestState.Error -> {
                    Log.d(TAG, "getGameById: ${result.exception}")
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "getGameById: Loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}