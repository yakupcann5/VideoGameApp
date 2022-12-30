package com.yakupcan.videogameapp.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.data.model.Results
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.db.entities.AllGameEntities
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.use_case.GetGameUseCase
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
    private val database: Database
) : ViewModel() {
    private val TAG = "HomeViewModel"
    var searchList = MutableLiveData<ArrayList<AllGameEntities>>()
    var gameList = MutableLiveData<ArrayList<Game>>()
    private val _game = MutableStateFlow<RequestState<ArrayList<Results>>?>(null)
    var game: StateFlow<RequestState<ArrayList<Results>>?> = _game
    private var refreshTime = 10 * 60 * 1000 * 1000L


    fun refresh() {
        val savedTime = preferences.getLong("time", 0)
        if (System.nanoTime() - savedTime < refreshTime) {
            getGamesFromDb()
            if (gameList.value.isNullOrEmpty()) {
                getGames()
            }
        } else {
            getGames()
        }
    }

    fun getGames() {
        getGameUseCase().onEach { result ->
            result.data?.let { it ->
                val game = arrayListOf<Game>()
                it.forEach { dto ->
                    game.add(
                        Game(
                            dto.id!!,
                            dto.name!!,
                            dto.released!!,
                            dto.backgroundImage!!,
                            dto.rating!!,
                            dto.ratingTop!!
                        )
                    )
                }
                gameList.value = game
                saveToDb(gameConvertEntities(game))
            }
            Log.d(TAG, "getGames: from api")
        }.launchIn(viewModelScope)
    }

    private fun saveToDb(game: List<AllGameEntities>) {
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
        viewModelScope.launch {
            val games = database.getGameDao().getAllGame()
            gameList.value = entitiesConvertGame(games)
        }
        Log.d(TAG, "getGamesFromDb: from db")
    }

    fun entitiesConvertGame(gamesList: List<AllGameEntities>): ArrayList<Game> {
        val game = arrayListOf<Game>()
        gamesList.forEach { dto ->
            game.add(
                Game(
                    dto.id,
                    dto.name,
                    dto.released,
                    dto.backgroundImage,
                    dto.rating,
                    dto.ratingTop
                )
            )
        }
        return game
    }

    private fun gameConvertEntities(game: ArrayList<Game>): ArrayList<AllGameEntities> {
        val gameEntities = arrayListOf<AllGameEntities>()
        game.forEach { dto ->
            gameEntities.add(
                AllGameEntities(
                    dto.id,
                    dto.name,
                    dto.released,
                    dto.backgroundImage,
                    dto.rating,
                    dto.ratingTop
                )
            )
        }
        return gameEntities
    }

    fun setCurrentFragment(fragment: String) {
        preferences.setString("currentFragment", fragment)
    }

    fun getContentsFromDB(query: String) {
        val searchQuery = "%$query%"
        viewModelScope.launch {
            val list = database.getGameDao().getContentsBySearchQuery(searchQuery)
            if (list.isNotEmpty()) {
                searchList.value = list as ArrayList<AllGameEntities>
            } else {
                searchList.value = arrayListOf()
            }
        }
    }
}