package com.example.mtggamemaster.viewmodels.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.data.card.api.CardAPI
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: CardRepository
) : ViewModel() {
    private val _cardList = MutableStateFlow(listOf<Card>())

    val cardList: StateFlow<List<Card>> = _cardList.asStateFlow()

    fun getCardByID(cardID: String) = repository.getByID(cardID)

    init {
        viewModelScope.launch {
            val api = CardAPI()
//            val result = api.getCardByID("409741")

//            result?.let { Log.i("MFR_DEBUG - CardViewModel_init", it.name) }

            repository.getAll().distinctUntilChanged()
                .collect { cards ->
                    if (cards != null) {
                        _cardList.value = cards
                    } else Log.i("MFR_DEBUG - CardViewModel init", "no cards found")
                }
        }
    }
}