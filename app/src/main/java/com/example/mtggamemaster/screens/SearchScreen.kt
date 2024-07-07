package com.example.mtggamemaster.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.SearchWidget
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton

@Composable
fun SearchScreen(navController: NavController) {
    val repository = MTGRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewModel: CardViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Cards",
        navController = navController,
        floatingActionButton = {
            SimpleFloatingActionButton(
                text = "",
                icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                onClick = {
                    navController.navigate("${Screens.cardscreen}")
                }
            )
        },
        content = { innerPadding ->
            SearchWidget(innerPadding)
        }
    )
}

