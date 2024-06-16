package com.example.mtggamemaster.data.card.api

import com.example.mtggamemaster.CardEndpoints
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitGsonCardAPI {
    @GET("{endpoint}/{query}")
    suspend fun getCards(
        @Path("endpoint") endpoint: CardEndpoints,
        @Query("query") query: String
    ): GsonCardResponse
}