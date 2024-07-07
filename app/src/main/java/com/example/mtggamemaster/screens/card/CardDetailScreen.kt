package com.example.mtggamemaster.screens.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.viewmodels.deck.DeckViewModel
import com.example.mtggamemaster.viewmodels.deck.DeckViewModelFactory
import com.example.mtggamemaster.widgets.AddToDeckDialog
import com.example.mtggamemaster.widgets.CardWidgetDetail
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton

@Composable
fun CardDetailScreen(cardID: String, navController: NavController) {
    val repository = MTGRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewmodel: CardViewModel = viewModel(factory = factory)
    val deckFactory = DeckViewModelFactory(repository = repository)
    val deckViewModel: DeckViewModel = viewModel(factory = deckFactory)
    val currentCard = viewmodel.getCardByID(cardID)
        ?: return Text(text = "Could not find card with id $cardID") /*.collectAsState(initial = null).value*/


    BaseScreen(
        title = currentCard.name!!,
        navController = navController,
        floatingActionButton = {
            val deckListState by deckViewModel.deckList.collectAsState()

            var dialogOpen by remember { mutableStateOf(false) }

            SimpleFloatingActionButton(
                text = "Add to deck",
                onClick = { dialogOpen = true }
            )
            if (dialogOpen) {
                AddToDeckDialog(
                    optionList = deckListState,
                    onDismissRequest = { dialogOpen = false },
                    onConfirmation = { selectedDecks ->
                        deckViewModel.addCardToDecks(currentCard.id!!, selectedDecks)
                    }
                )
            }
        },
        content = { innerPadding ->
            CardWidgetDetail(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                currentCard = currentCard,
                navController = navController,
                onFavoriteClick = { viewmodel.toggleFavorite(currentCard.id!!) }
            )
        }
    )
}