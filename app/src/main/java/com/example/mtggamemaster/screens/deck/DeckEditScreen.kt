package com.example.mtggamemaster.screens.deck

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.deck.DeckRepository
import com.example.mtggamemaster.models.Deck
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory
import com.example.mtggamemaster.widgets.DeckEditWidget
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton

@Composable
fun DeckEditScreen(deckID: String, navController: NavController) {
    val repository = DeckRepository()
    val factory = DeckViewModelFactory(repository = repository)
    val viewmodel: DeckViewModel = viewModel(factory = factory)

    val currentDeck = if (deckID == "+") {
        Deck()
    } else {
        viewmodel.getDeckByID(deckID).collectAsState(initial = null).value
    }

    if (currentDeck == null) return Text(text = "Could not find card with id $deckID")

    val curr by remember { mutableStateOf(currentDeck) }

    BaseScreen(
        title = "Edit ${curr.name}",
        navController = navController,
        floatingActionButton = {
            SimpleFloatingActionButton(
                text = "Save",
                onClick = {
                    if(deckID == "+") viewmodel.add(curr)
                    else viewmodel.update(curr)
                    navController.navigate("${Screens.deckscreen}")
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "${Screens.deckeditscreen} not implemented yet")
                Text("deckID is $deckID")

                DeckEditWidget(deck = curr, navController = navController)
            }
        }
    )
}