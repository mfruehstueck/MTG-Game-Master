package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.player.PlayerViewModelFactory
import com.example.mtggamemaster.viewmodels.player.PlayersViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val repository = MTGRepository()
    val factory = PlayerViewModelFactory(repository = repository)
    val viewmodel: PlayersViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Home",
        navController = navController,
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "${Screens.homescreen} not implemented yet")
                val gamesessions = viewmodel.getAll()
                val players: MutableSet<Player> = mutableSetOf()

                gamesessions?.forEach { gamesession ->
                    players.addAll(gamesession.players)
                }

                LazyColumn {
                    items(players.toList()) { player ->
                        Row {
                            Text(text = player.id)
                            Text(text = player.name)
                            Text(text = player.wins.toString())
                        }
                    }
                }
            }
        }
    )
}