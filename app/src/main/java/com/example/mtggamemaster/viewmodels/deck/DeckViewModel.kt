package com.example.mtggamemaster.viewmodels.deck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.models.Deck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class DeckViewModel (
    private val repository: DeckRepository
) : ViewModel() {
    private val _deckList = MutableStateFlow(listOf<Deck>())

    val deckList: StateFlow<List<Deck>> = _deckList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().distinctUntilChanged()
                .collect { decks ->
                    _deckList.value = decks
                }

        }
    }
}