package com.example.mtggamemaster.viewmodels.deck

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.Deck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class DeckViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    private val _deckList = MutableStateFlow(listOf<Deck>())
    val deckList: StateFlow<List<Deck>> = _deckList.asStateFlow()

    fun add(deck: Deck) = repository.deck_add(deck)
    fun update(deck: Deck) = repository.deck_update(deck)
    fun delete(deckID: String) = repository.deck_delete(deckID)
    fun getDeckByID(deckID: String) = repository.deck_getByID(deckID)

    fun toggleFavorite(deckID: String) {
        viewModelScope.launch {
            _deckList.collect { decks ->
                val foundDeck = decks.find { foundDeck -> foundDeck.id == deckID }
                    ?: throw NullPointerException()
                foundDeck.isFavorite = !foundDeck.isFavorite
            }
        }
    }

    fun addCardToDecks(cardID: String, decks: List<Deck>) {
        viewModelScope.launch {
            decks.forEach { currentDeck ->
                repository.deck_addCard(currentDeck.id, cardID)
            }
        }
    }

    fun removeCardFromDeck(deckID: String, cardID: String) {
        viewModelScope.launch {
            repository.deck_removeCard(deckID, cardID)
        }
    }

    init {
        viewModelScope.launch {
            repository.deck_getAll().distinctUntilChanged()
                .collect { decks ->
                    if (decks != null) {
                        _deckList.value = decks.sortedByDescending { it.isFavorite }
                    } else Log.i("MFR_DEBUG - DeckViewModel init", "no decks found")
                }
        }
    }
}