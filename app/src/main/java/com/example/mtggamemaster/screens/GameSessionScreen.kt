package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.GameSession
import com.example.mtggamemaster.viewmodels.player.PlayerViewModelFactory
import com.example.mtggamemaster.viewmodels.player.PlayersViewModel
import com.example.mtggamemaster.widgets.NewPlayerGrid

@Composable
fun GameSessionScreen(gamesessionID: String, navController: NavController) {
    val repository = MTGRepository()
    val factory = PlayerViewModelFactory(repository = repository)
    val viewModel: PlayersViewModel = viewModel(factory = factory)
    val currentGameSession: GameSession =
        viewModel.getAll()?.last()
//        viewModel.getByID(gamesessionID)
            ?: return Text(text = "Could not find gamesession with id $gamesessionID") /*.collectAsState(initial = null).value*/

    BaseScreen(
        title = "Game Session",
        navController = navController,
        content = { innerPadding ->
            Scaffold(
                modifier = Modifier.padding(innerPadding),
                content = { innerPadding ->
                    NewPlayerGrid(
                        gamesessionID = currentGameSession.id,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            )
        }
    )
}