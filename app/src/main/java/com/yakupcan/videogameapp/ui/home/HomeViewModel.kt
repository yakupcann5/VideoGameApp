package com.yakupcan.videogameapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakupcan.videogameapp.common.RequestState
import com.yakupcan.videogameapp.data.model.Results
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.use_case.GetGameUseCase
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferences: MyPreferences,
    private var getGameUseCase: GetGameUseCase)
    : ViewModel()
{
    private val TAG = "HomeViewModel"
    var gameList = MutableLiveData<ArrayList<Game>>()
    private val _game = MutableStateFlow<RequestState<ArrayList<Results>>?>(null)
    var game: StateFlow<RequestState<ArrayList<Results>>?> = _game

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
            }
        }.launchIn(viewModelScope)
    }

    fun setCurrentFragment(fragment: String) {
        preferences.setString("currentFragment",fragment)
    }
}