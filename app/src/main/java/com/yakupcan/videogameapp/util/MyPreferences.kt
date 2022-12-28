package com.yakupcan.videogameapp.util

import android.content.SharedPreferences
import javax.inject.Inject

class MyPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun getString(tag: String, default: String?): String? {
        return sharedPreferences.getString(tag, default)
    }

    fun setString(tag: String, value: String) {
        sharedPreferences.edit().putString(tag, value).apply()
    }

    fun getBoolean(tag: String): Boolean {
        return sharedPreferences.getBoolean(tag, true)
    }

    fun setBoolean(tag: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(tag, value).apply()
    }

    fun getInt(tag: String, default: Int): Int {
        return sharedPreferences.getInt(tag, default)
    }

    fun setInt(tag: String, value: Int) {
        sharedPreferences.edit().putInt(tag, value).apply()
    }
}