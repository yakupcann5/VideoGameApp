package com.yakupcan.videogameapp.ui.main

import androidx.lifecycle.ViewModel
import com.yakupcan.videogameapp.util.MyPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val preferences: MyPreferences) :
    ViewModel() {
    fun getCurrentFragment(): String? {
        return preferences.getString("currentFragment", null)
    }
}