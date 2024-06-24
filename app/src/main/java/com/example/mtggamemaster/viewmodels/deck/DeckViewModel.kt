package com.example.mtggamemaster.viewmodels.deck

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.models.Deck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class DeckViewModel(
    private val repository: DeckRepository
) : ViewModel() {
    private val _deckList = MutableStateFlow(listOf<Deck>())



    val deckList: StateFlow<List<Deck>> = _deckList.asStateFlow()

    fun add(deck: Deck) = repository.add(deck)
    fun update(deck: Deck) = repository.update(deck)
    fun delete(deckID: String) = repository.delete(deckID)
    fun getDeckByID(deckID: String) = repository.getByID(deckID)

    fun toggleFavorite(deckID: String){
      viewModelScope.launch {
          _deckList.collect {decks ->
              val foundDeck = decks.find { foundDeck -> foundDeck.id == deckID }
                  ?: throw NullPointerException()
              foundDeck.isFavorite = !foundDeck.isFavorite
          }
      }
    }

    init {
        viewModelScope.launch {
            repository.getAll().distinctUntilChanged()
                .collect { decks ->
                    if (decks != null) {
                        _deckList.value = decks
                    } else Log.i("MFR_DEBUG - DeckViewModel init", "no decks found")
                }
        }
    }
}