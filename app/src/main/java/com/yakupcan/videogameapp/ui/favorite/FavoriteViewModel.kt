package com.yakupcan.videogameapp.ui.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.db.entities.AllGameEntities
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.use_case.DenemeUseCase
import com.yakupcan.videogameapp.domain.use_case.GetFavoriteUseCase
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val preferences: MyPreferences,
    private val database: Database,
    private val favoriteUseCase: GetFavoriteUseCase,
    private val denemeUseCase: DenemeUseCase
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
                    (result.data as ArrayList<FavoriteGameEntities>).forEach {
                        getGameById(it.id)
                        Log.d(TAG, "getFavoriteGames: ${it.id}")
                    }
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

    fun getGameById(id: Int) {
        denemeUseCase.invoke(id).onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    list.add(result.data as Game)
                    allFavGame.postValue(list)
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