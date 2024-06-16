package com.example.mtggamemaster.data.card.api

import com.example.mtggamemaster.CardEndpoints
import com.example.mtggamemaster.ENV.Companion.API_URL
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitGsonCardService {
    private val api: RetrofitGsonCardAPI by lazy {
        createCardAPI()
    }

    suspend fun getCards(endpoint: CardEndpoints, query: String): GsonCardResponse {
        return api.getCards(endpoint, query)
    }

    private fun createCardAPI(): RetrofitGsonCardAPI {
        val gsonConverterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL.toString())
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(RetrofitGsonCardAPI::class.java)
    }

}