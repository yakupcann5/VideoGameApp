package com.yakupcan.videogameapp.data

import com.yakupcan.videogameapp.data.model.AllGameResponse
import com.yakupcan.videogameapp.data.model.SingleGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String
    ): AllGameResponse

    @GET("/api/games/{id}")
    suspend fun getDetailsOfGame(
        @Path("id") id: String,
        @Query("key") apiKey: String
    ): SingleGameResponse
}