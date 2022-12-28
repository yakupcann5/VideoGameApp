package com.yakupcan.videogameapp.common

import android.app.Activity
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yakupcan.videogameapp.R

object Constants {
    const val SHARED_PREF_FILE = "Game"
    const val BASE_URL = "https://api.rawg.io/api/"
    const val API_KEY = "c6c664fcb4414e2a951c2d445e348cc7"

    fun getBottomViewVisibility(isItVisible: Boolean, activity: Activity) {
        val botNav = activity.findViewById<BottomNavigationView>(R.id.bot_nav)
        botNav.visibility = if (isItVisible) View.VISIBLE else View.GONE
    }
}