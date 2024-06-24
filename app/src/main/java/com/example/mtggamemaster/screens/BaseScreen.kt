package com.example.mtggamemaster.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(
    title: String,
    content: @Composable (PaddingValues) -> Unit = {},
    navController: NavController,
    floatingActionButton: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = title, navController = navController, actions = actions)
        },
        bottomBar = {
            SimpleBottomAppBar(navController = navController)
        },
        floatingActionButton = floatingActionButton,
        content = { innerPadding -> content(innerPadding) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: String,
    navController: NavController,
    actions: @Composable () -> Unit = {}
) {
    Row {
        CenterAlignedTopAppBar(
            title = { Text(title) },
            navigationIcon = {
                if (navController.currentBackStackEntry?.destination?.route.toString() == "${Screens.homescreen}") return@CenterAlignedTopAppBar
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Return"
                    )
                }
            },
            actions = {
                actions()
            }
        )
    }
}

@Composable
fun SimpleBottomAppBar(navController: NavController) {
    val screens = listOf(
        Screen.Home,
        Screen.Decks,
    )

    NavigationBar {
        var selected =

            NavigationBarItem(
                selected = true,
                onClick = {
                    navController.navigate("${Screens.homescreen}")
                },
                icon = { Icon(Icons.Filled.Home, contentDescription = "") },
                label = { Text(text = "Home") }
            )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("${Screens.deckscreen}")
            },
            icon = { Icon(Icons.Filled.Menu, contentDescription = "") },
            label = { Text(text = "Decks") })
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("${Screens.cardscreen}")
            },
            icon = { Icon(Icons.Filled.Search, contentDescription = "") },
            label = { Text(text = "Cards") })
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("${Screens.favoritescreen}")
            },
            icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = "") },
            label = { Text(text = "Favorits") })

        //TODO: change to gamesscreen, gamesessionscreen is shown when selecting a game in gamesscreen
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("${Screens.gamesessionscreen}")
            },
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "") },
            label = { Text(text = "Games") })
    }
}