package com.example.mtggamemaster.screens.deck

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory

@Composable
fun DeckDetailScreen(deckID: String, navController: NavController) {
    val repository = DeckRepository()
    val factory = DeckViewModelFactory(repository = repository)
    val viewmodel: DeckViewModel = viewModel(factory = factory)
    val currentDeck = viewmodel.getDeckByID(deckID).collectAsState(initial = null).value

    if (currentDeck == null) return Text(text = "Could not find card with id $deckID")

    BaseScreen(
        title = currentDeck.name,
        navController = navController,
        content = { innerPadding ->
            Text(text = "${Screens.deckdetailscreen} not implemented yet")
        }
    )
}