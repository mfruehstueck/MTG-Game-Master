package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.CardWidgetGrid

@Composable
fun CardScreen(navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewModel: CardViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Cards",
        navController = navController,
        content = { innerPadding ->
            val cardListState by viewModel.cardList.collectAsState()

            Column {
                CardWidgetGrid(
                    cardList = cardListState,
                    navController = navController,
                    innerPadding = innerPadding
                )
            }
        }
    )
}
