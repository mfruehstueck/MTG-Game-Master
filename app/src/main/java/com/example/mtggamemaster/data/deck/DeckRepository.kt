package com.example.mtggamemaster.data.deck

import com.example.mtggamemaster.models.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DeckRepository {
    fun add(deck: Deck): Nothing = throw NotImplementedError()
    fun update(deck: Deck): Nothing = throw NotImplementedError()
    fun delete(deck: Deck): Nothing = throw NotImplementedError()
    fun getByID(id: Int): Nothing = throw NotImplementedError()
    fun getAll(): Flow<List<Deck>> = flowOf(getSampleDeckList(0))
    fun getFavorite(): Nothing = throw NotImplementedError()

    companion object {
        fun getSampleDeckList(cnt: Int): MutableList<Deck> {
            val deckList = mutableListOf(
                Deck(
                    id = 0,
                    name = "Deck 1",
                    cardList = emptyList()
                ),
                Deck(
                    id = 1,
                    name = "Deck 2",
                    cardList = emptyList()
                )
            )

            for (i in 1..cnt)
                deckList.addAll(deckList)

            return deckList
        }
    }
}