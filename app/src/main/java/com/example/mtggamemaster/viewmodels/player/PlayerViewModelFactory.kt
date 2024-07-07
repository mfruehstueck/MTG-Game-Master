package com.example.mtggamemaster.viewmodels.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mtggamemaster.data.card.MTGRepository

@Suppress("UNCHECKED_CAST")
class PlayerViewModelFactory (private val repository: MTGRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayersViewModel::class.java)) {
            return PlayersViewModel(repository = repository) as T
        }
//        } else if(modelClass.isAssignableFrom(CardFavoriteViewModel::class.java)) {
//            return CardFavoriteViewModel(repository = repository) as T
//        } else if(modelClass.isAssignableFrom(::class.java)) {
//            return CardDetailViewModel(repository = repository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}