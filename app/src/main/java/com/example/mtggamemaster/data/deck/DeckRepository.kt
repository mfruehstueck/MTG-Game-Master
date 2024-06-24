package com.example.mtggamemaster.data.deck

import com.example.mtggamemaster.data.card.TempDatabase
import com.example.mtggamemaster.models.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DeckRepository {
    fun add(deck: Deck) {
        deck.id = "${Deck.cnt}"
        TempDatabase.decks.add(deck)
    }

    fun update(deck: Deck) {
        val currentDeck = TempDatabase.decks.find { foundDeck -> foundDeck.id == deck.id }
            ?: throw NullPointerException()

        currentDeck.update(deck)
    }

    fun delete(deckID: String) = TempDatabase.decks.remove(
        TempDatabase.decks.find { foundDeck -> foundDeck.id == deckID }
    )

    fun getByID(id: String): Flow<Deck?> =
        flowOf(TempDatabase.decks.find { deck -> deck.id == id })

    fun getAll(): Flow<List<Deck>?> = flowOf(TempDatabase.decks)
    fun getFavorite(): Flow<List<Deck>?> =
        flowOf(TempDatabase.decks.filter { deck -> deck.isFavorite })

    companion object {
        fun getSampleDeckList(cnt: Int): MutableList<Deck> {
            val deckList = mutableListOf<Deck>()

            for (i in 0..cnt) {
                deckList.add(
                    Deck(
                        id = "$i",
                        name = "Deck $i",
                        cardList = emptyList()
                    )
                )
            }

            return deckList
        }
    }
}