package com.example.mtggamemaster.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mtggamemaster.Screens

sealed class Screen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home: Screen(
        route = Screens.homescreen.toString(),
        title = "Home",
        icon = Icons.Filled.Home
    )

    data object Decks: Screen(
        route = Screens.deckscreen.toString(),
        title = "Decks",
        icon = Icons.Filled.Menu
    )
    data object Cards: Screen(
        route = Screens.cardscreen.toString(),
        title = "Cards",
        icon = Icons.Filled.Search
    )
    data object Favorites: Screen(
        route = Screens.favoritescreen.toString(),
        title = "Favorites",
        icon = Icons.Filled.FavoriteBorder
    )

    data object Games: Screen(
        route = Screens.gamesscreen.toString(),
        title = "Games",
        icon = Icons.Filled.PlayArrow
    )
}