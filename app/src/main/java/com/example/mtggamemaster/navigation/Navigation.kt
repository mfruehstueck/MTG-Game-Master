package com.example.mtggamemaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mtggamemaster.RoutArguments
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.screens.CardDetailScreen
import com.example.mtggamemaster.screens.CardScreen
import com.example.mtggamemaster.screens.DeckDetailScreen
import com.example.mtggamemaster.screens.DeckScreen
import com.example.mtggamemaster.screens.FavoriteScreen
import com.example.mtggamemaster.screens.GameSessionScreen
import com.example.mtggamemaster.screens.GamesScreen
import com.example.mtggamemaster.screens.HomeScreen

@Composable
fun Navigation(startup: Screens) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "$startup") {
        composable(route = "${Screens.homescreen}") { HomeScreen(navController) }

        composable(route = "${Screens.deckscreen}") { DeckScreen(navController) }
        composable(
            route = "${Screens.deckdetailscreen}/{${RoutArguments.deckID}",
            arguments = listOf(navArgument(name = "${RoutArguments.deckID}") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DeckDetailScreen(
                deckID = backStackEntry.arguments?.getString("${RoutArguments.deckID}")!!,
                navController= navController
            )
        }

        composable(route = "${Screens.cardscreen}") { CardScreen(navController) }
        composable(
            route = "${Screens.carddetailscreen}/{${RoutArguments.cardID}}",
            arguments = listOf(navArgument(name = "${RoutArguments.cardID}") {
                type = NavType.StringType
            })
        ) {backStackEntry ->
            CardDetailScreen(
                cardID = backStackEntry.arguments?.getString("${RoutArguments.cardID}")!!,
                navController = navController
            )
        }

        composable(route = "${Screens.favoritescreen}") { FavoriteScreen(navController) }

        composable(route = "${Screens.gamesscreen}") { GamesScreen(navController) }

        composable(route = "${Screens.gamesessionscreen}") { GameSessionScreen(navController) }
        //TODO: delete above in exchange for dynamic route below
        //  composable(
        //      route = "${Screens.gamesessionscreen}/{${RoutArguments.gameID}}",
        //      arguments = listOf(navArgument(name = "${RoutArguments.gameID}") {
        //          type = NavType.StringType
        //      })
        //  ) { backStackEntry -> GameSessionScreen(navController) }
    }
}