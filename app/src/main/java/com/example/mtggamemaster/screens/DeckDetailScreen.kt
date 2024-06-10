package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens

@Composable
fun DeckDetailScreen(navController: NavController) {
    BaseScreen(
        title = "Deck Detail",
        navController = navController,
        content = { innerPadding ->
            Text(
                modifier = Modifier.padding(innerPadding),
                text = "${Screens.deckdetailscreen} not implemented yet"
            )
        }
    )
}