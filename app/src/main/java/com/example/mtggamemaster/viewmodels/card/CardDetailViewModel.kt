package com.example.mtggamemaster.viewmodels.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val repository: MTGRepository
):ViewModel() {
//    private val _currentMovie = repository.card_getByID(cardID)

    fun toggleFavorite(cardID: String) {
        viewModelScope.launch {
            val foundCard = repository.card_getByID(cardID).firstOrNull()
            foundCard?.let {
                it.isFavorite = !it.isFavorite
                repository.card_update(it)
            }
        }
    }

    fun getCardByID(id: String) = repository.card_getByID(id)
}