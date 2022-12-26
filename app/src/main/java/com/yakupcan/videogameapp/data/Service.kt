package com.yakupcan.videogameapp.data

import retrofit2.http.GET

interface Service {
    @GET("games")
    suspend fun getGames(apiKey: String): GameResponse
}