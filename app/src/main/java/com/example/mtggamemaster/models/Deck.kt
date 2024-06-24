package com.example.mtggamemaster.models

import com.example.mtggamemaster.models.card.Card

data class Deck(
    var id: String,
    var name: String,
    var cardList: List<Card>,
    var isFavorite: Boolean = false
) {
    fun update(deck: Deck) {
        this.name = deck.name
        this.cardList = deck.cardList
        this.isFavorite = deck.isFavorite
    }

    companion object {
        var cnt: Int = 0
    }

    constructor() : this(id = "0", name = "", cardList = emptyList(), isFavorite = false) {
        cnt++
    }
}
