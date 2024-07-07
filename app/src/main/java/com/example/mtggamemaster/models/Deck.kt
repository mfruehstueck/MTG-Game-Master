package com.example.mtggamemaster.models

import com.example.mtggamemaster.models.card.Card

data class Deck(
    var id: String,
    var name: String,
    var cardList: MutableList<Card>,
    var isFavorite: Boolean = false
) {
    fun update(deck: Deck) {
        this.name = deck.name
        this.cardList = deck.cardList
        this.isFavorite = deck.isFavorite
    }

    companion object {
        private var cnt: Int = 0
    }

    constructor() : this(id = "${cnt++}", name = "", cardList = mutableListOf(), isFavorite = false)
}
