package com.example.mtggamemaster.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.CardWidgetGrid

@Composable
fun CardScreen(navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewModel: CardViewModel = viewModel(factory = factory)

    fun toggleFavorite(cardID: String) {
        viewModel.toggleFavorite(cardID)
    }

    BaseScreen(
        title = "Cards",
        navController = navController,
        content = { innerPadding ->
            val cardListState by viewModel.cardList.collectAsState()

            Column {
                CardWidgetGrid(
                    cardList = cardListState,
                    navController = navController,

                    onFavoriteClick = { cardID ->
                        toggleFavorite(cardID)
                    },

                    innerPadding = innerPadding
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("${Screens.searchscreen}") }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}
