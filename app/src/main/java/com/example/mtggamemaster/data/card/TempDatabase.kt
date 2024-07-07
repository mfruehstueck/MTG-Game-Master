package com.example.mtggamemaster.data.card

import com.example.mtggamemaster.models.Deck
import com.example.mtggamemaster.models.GameSession
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.models.card.Card

class TempDatabase {
    companion object {
        var cards: MutableList<Card> = mutableListOf() /*MTGAPI.getSampleCardList(3)*/
        var decks: MutableList<Deck> = mutableListOf() /*MTGRepository.getSampleDeckList(2)*/
        var gamesessions: MutableList<GameSession> = mutableListOf(
            GameSession()
        )
        var players: MutableList<Player> = mutableListOf()
    }

    init {
    }
}