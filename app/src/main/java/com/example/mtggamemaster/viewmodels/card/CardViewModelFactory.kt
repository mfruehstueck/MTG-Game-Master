package com.example.mtggamemaster.viewmodels.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mtggamemaster.data.card.MTGRepository

@Suppress("UNCHECKED_CAST")
class CardViewModelFactory(private val repository: MTGRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardViewModel::class.java)) {
            return CardViewModel(repository = repository) as T
        } else if(modelClass.isAssignableFrom(CardFavoriteViewModel::class.java)) {
            return CardFavoriteViewModel(repository = repository) as T
        } else if(modelClass.isAssignableFrom(CardDetailViewModel::class.java)) {
            return CardDetailViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}