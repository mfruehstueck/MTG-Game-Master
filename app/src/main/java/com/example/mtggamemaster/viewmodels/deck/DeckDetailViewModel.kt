package com.example.mtggamemaster.viewmodels.deck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DeckDetailViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    private val _deckCardList = MutableStateFlow(listOf<Card>())
    val deckCardList: StateFlow<List<Card>> = _deckCardList.asStateFlow()
//    val currentDeck = deckCardList.

    fun toggleFavorite(deckID: String) {
        viewModelScope.launch {
            _deckCardList.collect { decks ->
                val foundDeck = decks.find { foundDeck -> foundDeck.id == deckID }
                    ?: throw NullPointerException()
                foundDeck.isFavorite = !foundDeck.isFavorite
            }
        }
    }

//    fun removeCardFromDeck(deckID: String, cardID: String) {
//        viewModelScope.launch {
//            repository.deck_removeCard(deckID, cardID).distinctUntilChanged()
//                .collect { cards ->
//                    if (cards != null) {
//                        _deckCardList.value = cards
//                    } else Log.i("MFR_DEBUG - DeckViewModel removeCardFromDeck", "no cards found")
//                }
//        }
//    }

//    init {
//        viewModelScope.launch {
//            repository.deck_getCards().distinctUntilChanged()
//                .collect { decks ->
//                    if (decks != null) {
//                        _deckCardList.value = decks.sortedByDescending { it.isFavorite }
//                    } else Log.i("MFR_DEBUG - DeckViewModel init", "no decks found")
//                }
//        }
//    }
}