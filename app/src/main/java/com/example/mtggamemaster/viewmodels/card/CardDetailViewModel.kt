package com.example.mtggamemaster.viewmodels.card

import androidx.lifecycle.ViewModel
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow

class CardDetailViewModel(
    private val repository: CardRepository
):ViewModel() {
    private val _currentMovie = MutableStateFlow(Card())

    fun getCardByID(id: String) = repository.getByID(id)
}