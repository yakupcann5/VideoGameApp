package com.yakupcan.videogameapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String
    ): GameResponse

    @GET("/api/games/{id}")
    suspend fun getDetailsOfGame(
        @Path("id") id: String
    ): // game single tipinde dönüş olacak model yazılacak
}