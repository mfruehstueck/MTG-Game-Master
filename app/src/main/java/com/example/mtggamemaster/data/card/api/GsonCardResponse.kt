package com.example.mtggamemaster.data.card.api

import com.google.gson.annotations.SerializedName

data class GsonCardResponse(
    val cards: List<GsonCard>
)

data class GsonCard(
    @SerializedName("name") val name: String
)
