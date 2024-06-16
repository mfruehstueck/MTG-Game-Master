package com.example.mtggamemaster.data.card

import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CardRepository(/*private val cardDAO: CardDAO*/) {
    fun add(card: Card): Nothing = throw NotImplementedError()
    fun update(card: Card): Nothing = throw NotImplementedError()
    fun delete(card: Card): Nothing = throw NotImplementedError()
    fun getByID(id: String): Flow<Card?> =
        flowOf(TempDatabase.cards.find { card -> card.id == id })

    fun getAll(): Flow<List<Card>?> = flowOf(TempDatabase.cards)
    fun getSet(set: String): Nothing = throw NotImplementedError()
    fun getTypes(): Nothing = throw NotImplementedError()
    fun getSubTypes(): Nothing = throw NotImplementedError()
    fun getSuperTypes(): Nothing = throw NotImplementedError()
    fun getFormats(): Nothing = throw NotImplementedError()
    fun getFavorite(): Flow<List<Card>?> =
        flowOf(TempDatabase.cards.filter { card -> card.isFavorite })
}