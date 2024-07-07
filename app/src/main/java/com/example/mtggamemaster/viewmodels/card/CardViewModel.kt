package com.example.mtggamemaster.viewmodels.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.card.Card
import com.example.mtggamemaster.models.card.CardFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    companion object {
        private var _cardList = MutableStateFlow(emptyList<Card>())
        var currentFilter = CardFilter()
            .set("unf")

        fun defaultFilter() {
            CardViewModel.currentFilter = CardFilter()
                .set("unf")
        }
    }

    val cardList: StateFlow<List<Card>> = _cardList.asStateFlow()

    fun toggleFavorite(cardID: String) {
        viewModelScope.launch {
            val foundCard = repository.card_getByID(cardID).firstOrNull()
            foundCard?.let {
                it.isFavorite = !it.isFavorite
                repository.card_update(it)
            }
        }
    }

    fun getCardByID(cardID: String): Card? {
        var card: Card? = null

        viewModelScope.launch {
            _cardList.collect { cards ->
                card = cards.find { checkCard -> checkCard.id == cardID }
                    ?: throw NullPointerException()
            }
        }

        return card
    }

    init {
        viewModelScope.launch {
            repository.card_init {
                Log.i("", "init")
                viewModelScope.launch {
                    repository.card_getFiltered(currentFilter)
                        .collect { cards ->
                            if (cards != null) {
                                _cardList.value = cards
                            }
                        }
                }
            }
        }
    }
}