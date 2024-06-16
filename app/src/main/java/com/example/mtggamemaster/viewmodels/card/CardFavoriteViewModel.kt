package com.example.mtggamemaster.viewmodels.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CardFavoriteViewModel(
    private val repository: CardRepository
) : ViewModel() {
    private val _cardFavoriteList = MutableStateFlow(listOf<Card>())

    val cardFavoriteList: StateFlow<List<Card>> = _cardFavoriteList.asStateFlow()

    suspend fun toggleFavorite(card: Card) {
        card.isFavorite = !card.isFavorite

        //TODO: enable when db ready
        //  repository.update(card)
    }

    init {
        viewModelScope.launch {
            repository.getFavorite().distinctUntilChanged()
                .collect { cards ->
                    if (cards != null) {
                        _cardFavoriteList.value = cards
                    } else Log.i("MFR_DEBUG - CardFavoriteViewModel init", "no favorites found")
                }
        }
    }
}