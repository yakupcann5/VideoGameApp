package com.yakupcan.videogameapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val preferences: MyPreferences
) : ViewModel() {

    fun setCurrentFragment(fragment: String) {
        preferences.setString("currentFragment", fragment)
    }
}