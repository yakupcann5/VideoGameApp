package com.yakupcan.videogameapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.data.model.Results
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.use_case.GetAllGameUseCase
import com.yakupcan.videogameapp.domain.use_case.GetGameUseCase
import com.yakupcan.videogameapp.domain.use_case.GetSearchGameUseCase
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferences: MyPreferences,
    private var getGameUseCase: GetGameUseCase,
    private val database: Database,
    private val getAllGameUseCase: GetAllGameUseCase,
    private val getSearchGameUseCase: GetSearchGameUseCase
) : ViewModel() {
    private val TAG = "HomeViewModel"
    var searchList = MutableLiveData<ArrayList<Game>>()
    var gameList = MutableLiveData<ArrayList<Game>>()
    private val _game = MutableStateFlow<RequestState<ArrayList<Results>>?>(null)
    var game: StateFlow<RequestState<ArrayList<Results>>?> = _game
    val list = arrayListOf<Game>()


    fun refresh() {
        getGamesFromDb()
    }

    private fun getGames() {
        getGameUseCase().onEach { result ->
            result.data?.let { it ->
                gameList.value = it as ArrayList<Game>
                saveToDb(it)
            }
            Log.d(TAG, "getGames: from api")
        }.launchIn(viewModelScope)
    }

    private fun saveToDb(game: List<Game>) {
        viewModelScope.launch {
            database.getGameDao().deleteAllGame()
            val uuidList = database.getGameDao().insertAllGame(*game.toTypedArray())
            var i = 0
            while (i < game.size) {
                game[i].id = uuidList[i].toInt()
                ++i
            }
        }
        preferences.setLong("time", System.nanoTime())
    }

    private fun getGamesFromDb() {
        getAllGameUseCase.invoke().onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    if (result.data?.isNotEmpty() == true) {
                        gameList.value = result.data as ArrayList<Game>
                        Log.d(TAG, "getGamesFromDb: from db")
                    } else {
                        getGames()
                    }
                }
                is RequestState.Error -> {
                    getGames()
                    Log.d(TAG, "getFavoriteGames: ${result.exception}")
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "getFavoriteGames: Loading")
                }
            }
        }.launchIn(viewModelScope)
        Log.d(TAG, "getGamesFromDb: from db")
    }

    fun setCurrentFragment(fragment: String) {
        preferences.setString("currentFragment", fragment)
    }

    fun getContentsFromDB(query: String) {
        val searchQuery = "%$query%"
        getSearchGameUseCase.invoke(searchQuery).onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    result.data?.let { game ->
                        if (game.isNotEmpty()) {
                            searchList.value = game as ArrayList<Game>
                        } else {
                            searchList.value = arrayListOf()
                        }
                    }
                    Log.d(TAG, "getGamesFromDb: from db")
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
}