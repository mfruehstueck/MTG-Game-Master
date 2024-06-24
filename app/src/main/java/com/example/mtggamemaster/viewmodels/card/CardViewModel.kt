package com.example.mtggamemaster.viewmodels.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.data.card.api.MTGAPI
import com.example.mtggamemaster.models.card.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardViewModel(
    private val repository: CardRepository
) : ViewModel() {
    companion object {
        private var _cardList = MutableStateFlow(emptyList<Card>())
    }

    val cardList: StateFlow<List<Card>> = _cardList.asStateFlow()

    fun toggleFavorite(cardID: String) {
//        val foundCard = _cardList.find { check -> check.id == cardID }
//            ?: throw NullPointerException()
//        foundCard.isFavorite = !foundCard.isFavorite
        viewModelScope.launch {
            cardList.collect { cards ->
                val foundCard = cards.find { foundCard -> foundCard.id == cardID }
                    ?: throw NullPointerException()
                foundCard.isFavorite = !foundCard.isFavorite
            }
            //TODO: change above for dbCall
            //  repository.getByID(card.id).firstOrNull()?.let {foundCard ->
            //  foundCard.isFavorite = !foundCard.isFavorite
            //
            //  }
        }
    }

    fun getCardByID(cardID: String): Card? {
        var card: Card? = null

        viewModelScope.launch {
            _cardList.collect { cards ->
                card = cards.find { checkCard -> checkCard.id == cardID }
                    ?: throw NullPointerException()
            }
//            MTGAPI.queryCards(
//                URLBuilder().id(cardID).buildCardURL().toString()
//            ) { queriedCards ->
//                card = queriedCards[0]
//            }
        }

        return card
    }

    init {
//        CardAPI.httpRequest(
//            "https://api.magicthegathering.io/v1/cards/?multiverseid=129513"
//        ) { resp ->
//            println(resp)
//        }


        viewModelScope.launch {
            if (_cardList.value.isEmpty()) {
                MTGAPI.queryCards("https://api.magicthegathering.io/v1/cards?set=UNF") { queriedCards ->
                    queriedCards.forEach { queriedCard ->
                        _cardList.update { it + queriedCard }
                    }
                    println()
                }
            }

//            repository.getAll().distinctUntilChanged()
//                .collect { cards ->
//                    if (cards != null) {
//                        _cardList.value = cards
//                        //"https://api.magicthegathering.io/v1/cards/?multiverseid=129513"
//                        MTGAPI.queryCards("https://api.magicthegathering.io/v1/cards/?setName=Unfinity") { queriedCards ->
//                            queriedCards.forEach { queriedCard ->
//                                _cardList.update { it + queriedCard }
//                            }
//                            println()
//                        }
//                        _cardList.collect { cardss -> TempDatabase.cards = cardss.toMutableList() }
//                    } else Log.i("MFR_DEBUG - CardViewModel init", "no cards found")
//                }
        }
    }
}