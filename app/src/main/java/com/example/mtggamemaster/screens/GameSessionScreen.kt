package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.PlayersViewModel
import com.example.mtggamemaster.widgets.PlayerGrid

@Composable
fun GameSessionScreen(navController: NavController) {
    val viewModel: PlayersViewModel = viewModel()
    val testingPlayerList = ArrayDeque<Player>(
        listOf(
            Player(name = "Manuel", life = 20),
            Player(name = "Alex", life = 20),
            Player(name = "Max", life = 20)
        )
    )

    BaseScreen(
        title = "Game Session",
        navController = navController,
        content = { innerPadding ->
            Scaffold(
                modifier = Modifier.padding(innerPadding),
                floatingActionButton = {
                    ExtendedFloatingActionButton(onClick = { viewModel.addPlayer(testingPlayerList.removeFirst()) }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                        Text(text = "Add Player")
                    }
                },
                content = { innerPadding -> PlayerGrid(viewModel = viewModel, modifier = Modifier.padding(innerPadding)) }
            )
        }
    )
}