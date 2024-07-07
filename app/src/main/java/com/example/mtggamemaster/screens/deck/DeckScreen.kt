package com.example.mtggamemaster.screens.deck

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory
import com.example.mtggamemaster.widgets.DeckWidgetList
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton

@Composable
fun DeckScreen(/*action: String, */navController: NavController) {
    val repository = MTGRepository()
    val factory = DeckViewModelFactory(repository = repository)
    val viewmodel: DeckViewModel = viewModel(factory = factory)

    fun toggleFavorite(deckID: String) {
        viewmodel.toggleFavorite(deckID)
    }

    BaseScreen(
        title = "Decks",
        content = { innerPadding ->
            val deckListState by viewmodel.deckList.collectAsState()

            Column {
                Text(text = "${Screens.deckscreen} not implemented yet")
                DeckWidgetList(
                    deckList = deckListState,
                    navController = navController,

                    onFavoriteClick = { deckID ->
                        toggleFavorite(deckID)
                        navController.navigate("${Screens.deckscreen}")
                    },
                    onDeleteClick = { deckID ->
                        viewmodel.delete(deckID)
                        navController.navigate("${Screens.deckscreen}")
                    },

                    innerPadding = innerPadding
                )
            }
        },
        navController = navController,
        floatingActionButton = {
            SimpleFloatingActionButton(
                text = "Add Deck",
                onClick = { navController.navigate("${Screens.deckeditscreen}/+") }
            )
        }
    )
}