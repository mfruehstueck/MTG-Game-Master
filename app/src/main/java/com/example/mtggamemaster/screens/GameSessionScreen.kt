package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.player.PlayerViewModelFactory
import com.example.mtggamemaster.viewmodels.player.PlayersViewModel
import com.example.mtggamemaster.widgets.AddPlayerActionButton
import com.example.mtggamemaster.widgets.PlayerGrid

@Composable
fun GameSessionScreen(navController: NavController) {
    val repository = MTGRepository()
    val factory = PlayerViewModelFactory(repository = repository)
    val viewModel: PlayersViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Game Session",
        navController = navController,
        content = { innerPadding ->
            Scaffold(
                modifier = Modifier.padding(innerPadding),
                floatingActionButton = {
                    AddPlayerActionButton(onConfirmation = { name ->
                        viewModel.addPlayer(
                            player = Player(
                                name = name
                            )
                        )
                    })
                },
                content = { innerPadding ->
                    PlayerGrid(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            )
        }
    )
}