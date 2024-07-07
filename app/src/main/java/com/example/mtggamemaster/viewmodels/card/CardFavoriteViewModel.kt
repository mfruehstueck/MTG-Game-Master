package com.example.mtggamemaster.viewmodels.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CardFavoriteViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    private val _cardFavoriteList = MutableStateFlow<List<Card>>(emptyList())
    val cardFavoriteList: StateFlow<List<Card>> = _cardFavoriteList.asStateFlow()

    fun toggleFavorite(cardID: String) {
        viewModelScope.launch {
            val foundCard = repository.card_getByID(cardID).firstOrNull()
            foundCard?.let {
                it.isFavorite = !it.isFavorite
                repository.card_update(it)

                //N: updates the view list
                repository.card_getFavorite().collect { cards ->
                    if (cards != null) {
                        _cardFavoriteList.value = cards
                    } else Log.i("MFR_DEBUG - CardFavoriteViewModel toggleFavorite", "no favorites found")
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.card_getFavorite().distinctUntilChanged()
                .collect { cards ->
                    if (cards != null) {
                        _cardFavoriteList.value = cards
                    } else Log.i("MFR_DEBUG - CardFavoriteViewModel init", "no favorites found")
                }
        }
    }
}