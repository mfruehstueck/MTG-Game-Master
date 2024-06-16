package com.example.mtggamemaster.models

import com.example.mtggamemaster.models.card.Card

data class Deck(
    var id: Int,
    var name: String,
    var cardList: List<Card>,
    var isFavorite: Boolean = false
)
