package com.example.mtggamemaster.screens.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.card.CardFavoriteViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.CardWidgetGrid

@Composable
fun FavoriteScreen(navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewModel: CardFavoriteViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Favorites",
        navController = navController,
        content = { innerPadding ->
            val favoriteCardListState by viewModel.cardFavoriteList.collectAsState()

            CardWidgetGrid(
                cardList = favoriteCardListState,
                navController = navController,
                innerPadding = innerPadding
            )
        }
    )
}