package com.example.mtggamemaster.data.card

import com.example.mtggamemaster.data.card.api.CardAPI
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.models.Deck
import com.example.mtggamemaster.models.card.Card

class TempDatabase {
    companion object{
        var cards: MutableList<Card> = CardAPI.getSampleCardList(3)
        var decks: MutableList<Deck> = DeckRepository.getSampleDeckList(2)
    }

    init {
    }
}