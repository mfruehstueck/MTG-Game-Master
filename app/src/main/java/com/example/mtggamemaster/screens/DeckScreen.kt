package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory
import com.example.mtggamemaster.widgets.DeckWidgetList

@Composable
fun DeckScreen(navController: NavController) {
    val repository = DeckRepository()
    val factory = DeckViewModelFactory(repository = repository)
    val viewModel: DeckViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Decks",
        navController = navController,
        content = { innerPadding ->
            val deckListState by viewModel.deckList.collectAsState()

            Column {
                Text(text = "${Screens.deckscreen} not implemented yet")
                DeckWidgetList(
                    deckList = deckListState,
                    navController = navController,
                    innerPadding = innerPadding
                )
            }
        }
    )
}