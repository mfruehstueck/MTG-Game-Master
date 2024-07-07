package com.example.mtggamemaster.screens.deck

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory
import com.example.mtggamemaster.widgets.CardWidgetGrid

@Composable
fun DeckDetailScreen(deckID: String, navController: NavController) {
    val repository = MTGRepository()
    val factory = DeckViewModelFactory(repository = repository)
    val viewmodel: DeckViewModel = viewModel(factory = factory)
    val cardFactory = CardViewModelFactory(repository = repository)
    val cardViewModel: CardViewModel = viewModel(factory = cardFactory)
    val currentDeck = viewmodel.getDeckByID(deckID).collectAsState(initial = null).value
        ?: return Text(text = "Could not find card with id $deckID")

    BaseScreen(
        title = currentDeck.name,
        navController = navController,
        content = { innerPadding ->
//            Text(text = "${Screens.deckdetailscreen} not implemented yet")
            CardWidgetGrid(
                cardList = currentDeck.cardList,
                navController = navController,

                isRemoveable = true,

                innerPadding = innerPadding,
                onFavoriteClick = { cardID ->
                    cardViewModel.toggleFavorite(cardID)
                    navController.navigate("${Screens.deckdetailscreen}/${currentDeck.id}")
                },
                onRemoveClick = { cardID ->
                    viewmodel.removeCardFromDeck(currentDeck.id, cardID)
                    navController.navigate("${Screens.deckdetailscreen}/${currentDeck.id}")
                }
            )
        }
    )
}